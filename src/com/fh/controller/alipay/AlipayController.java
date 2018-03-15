package com.fh.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fh.entity.watermeter.BillService;
import com.fh.entity.watermeter.Order;
import com.fh.entity.watermeter.WatUser;
import com.fh.framewrok.config.AliPayConfig;
import com.fh.framewrok.response.Token;
import com.fh.framewrok.response.WatResponse;
import com.fh.framewrok.util.Const;
import com.fh.framewrok.util.UserInfoCache;
import com.fh.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
@Controller
@RequestMapping(value = "/alipay")
public class AlipayController {

    private static final Logger logger= LoggerFactory.getLogger(AlipayController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private BillService billService;


    @RequestMapping(value = "createOrder")
    @ResponseBody
    public WatResponse createOrder(@RequestParam("payType") String payType,
                                   @RequestParam("payMoney") String payMoney,
                                   @RequestParam("kId") String kId,
                                   @RequestParam(value = "infoId", required = false) String infoId,
                                   HttpServletRequest request, HttpServletResponse response) {
        logger.info("创建订单开始=======================================================");
        String orderStr = "";
        WatResponse watResponse = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            watResponse.setStatus(Const.RESPONSE_STATUS_0);
            watResponse.setMsg("token过期，请重新登录");
            return watResponse;
        }
        WatUser user = token.getUser();
        try {
            Order order = orderService.createOrder(user.getId(), payMoney, payType,infoId);
            AlipayClient client = new DefaultAlipayClient(AliPayConfig.URL,AliPayConfig.APPID, AliPayConfig.RSA_PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.CHARSET,
                    AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.SIGNTYPE);
            AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody("用户充值：" + payMoney + "元");
            model.setGoodsType("0");
            model.setOutTradeNo(order.getId());
            model.setSubject("用户充值");
            model.setTimeoutExpress("90m");
            model.setTotalAmount(payMoney);
            model.setProductCode("QUICK_MSECURITY_PAY");
            model.setSellerId("2088102175232980");
            alipayRequest.setBizModel(model);
            alipayRequest.setNotifyUrl(AliPayConfig.notify_url);
            alipayRequest.setApiVersion(AliPayConfig.VERSION);
            AlipayTradeAppPayResponse payResponse = client.sdkExecute(alipayRequest);
            orderStr = payResponse.getBody();
            orderService.save(order);
            watResponse.setObj(orderStr);
            watResponse.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            watResponse.setMsg("支付调用,生成订单成功");
        } catch (Exception e) {
            watResponse.setObj(orderStr);
            watResponse.setStatus(Const.RESPONSE_STATUS_FAILED);
            watResponse.setMsg("支付调用,生成订单失败");
            logger.error(e.getMessage(),e);
        }
        return watResponse;
    }

    @RequestMapping(value = "notifyUrl")
    @ResponseBody
    public String notifyUrl(HttpServletRequest request, HttpServletResponse response) {
        logger.info("支付宝回调开始=======================================================");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> it = requestParams.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            String[] values = requestParams.get(key);
            String valuesStr = "";
            for (int i = 0; i < values.length; i++) {
                valuesStr = (i == values.length - 1) ? valuesStr + values[i] : valuesStr + values[i] + ",";
            }
            params.put(key, valuesStr);
        }
        String out_trade_no = request.getParameter("out_trade_no");
        String orderType = request.getParameter("body");
        String tradeStatus = request.getParameter("trade_status");
        boolean signVerified = false;
        String rspStatus = "";
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET,AliPayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            rspStatus = "fail";
            logger.error(e.getMessage(),e);
        }
        Order order = null;
        try {
            order = (Order) orderService.find(Order.class, out_trade_no);
            if (signVerified) {
                if (tradeStatus.equals(Const.TRADE_SUCCESS)) {
                    order.setStatus(Const.PAY_STATUS_SUCCESS);
                    orderService.update(order);
                    billService.saveBill(order);
                    rspStatus = "success";
                }
            } else {
                order.setStatus(Const.PAY_STATUS_FAIELD);
                rspStatus = "fail";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            rspStatus = "fail";
        }
        return rspStatus;
    }

    @RequestMapping(value = "returnUrl")
    @ResponseBody
    public WatResponse returnUrl(@RequestParam("tradeOutNo") String tradeOutNo){
        WatResponse watResponse = new WatResponse();
        Map<String,Object> returnMap=new HashMap<String,Object>();
        Order order=null;
        try {
            order = (Order) orderService.find(Order.class, tradeOutNo);
            if(order.getStatus().equals(Const.PAY_STATUS_SUCCESS)){
                watResponse.setStatus(Const.RESPONSE_STATUS_SUCCESS);
                watResponse.setMsg("支付成功");
                returnMap.put("status","success");
                returnMap.put("order",order);
                watResponse.setObj(returnMap);
            }else {
                watResponse.setStatus(Const.RESPONSE_STATUS_FAILED);
                watResponse.setMsg("支付失败");
                returnMap.put("status","fail");
                returnMap.put("order",order);
                watResponse.setObj(returnMap);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            watResponse.setStatus(Const.RESPONSE_STATUS_FAILED);
            watResponse.setMsg("支付失败");
            returnMap.put("status","fail");
            returnMap.put("order","");
            watResponse.setObj(returnMap);
        }
        return watResponse;
    }


}
