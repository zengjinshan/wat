package com.fh.service.order;

import com.fh.entity.watermeter.Order;
import com.fh.entity.watermeter.OrderType;
import com.fh.framewrok.service.BaseService;
import com.fh.framewrok.util.Const;
import com.fh.framewrok.util.UuidUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
@Service
public class OrderService extends BaseService{

    public Order createOrder(String userId,String payMoney,String payType){
        Order order=new Order();
        order.setId(UuidUtil.get32UUID());
        order.setChannel(Const.PAY_TYPE_ALIBABA);
        order.setPayMoney(payMoney);
        order.setCreateDate(new Date());
        order.setStatus(Const.PAY_STATUS_PAYING);
        order.setPayUserId(userId);
        if(payType.equals(Const.PAY_SERVICE_REWARD)){
            order.setDescrib("打赏支付");
        }
        if(payType.equals(Const.PAY_SERVICE_RECHARGE)){
            order.setDescrib("充值");
        }
        if(payType.equals(Const.PAY_SERVICE_OFFER)){
            order.setDescrib("悬赏支付");
        }
        order.setPayType(payType);
        return order;
    }

    public void saveOrder(Order order,String infoId) throws Exception {
        this.save(order);
        OrderType orderType=new OrderType();
        orderType.setId(UuidUtil.get32UUID());
        orderType.setOrderId(order.getId());
        orderType.setServiceId(infoId);
        orderType.setType("1");
        this.save(orderType);
    }
}
