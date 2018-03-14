package com.fh.controller.alipay;

import com.fh.entity.watermeter.Order;
import com.fh.entity.watermeter.WatUser;
import com.fh.framewrok.response.Token;
import com.fh.framewrok.response.WatResponse;
import com.fh.framewrok.util.Const;
import com.fh.framewrok.util.UserInfoCache;
import com.fh.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
@RequestMapping(value = "/alipay")
public class AlipayController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "createOrder")
    @ResponseBody
    public WatResponse createOrder(@RequestParam("payType") String payType,
                                   @RequestParam("payMoney") String payMoney,
                                   @RequestParam("kId") String kId,
                                   @RequestParam(value = "infoId",required = false) String infoId,
                                   HttpServletRequest request, HttpServletResponse response) {
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
            Order order = orderService.createOrder(user.getId(), payMoney, payType);
        } catch (Exception e) {

        }
        return null;
    }
}
