package com.fh.entity.watermeter;

import com.fh.framewrok.service.BaseService;
import com.fh.framewrok.util.Const;
import com.fh.framewrok.util.UuidUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/3/15 0015.
 */
@Service
public class BillService extends BaseService {

    public void saveBill(Order order) throws Exception{
        //充值
        Bill bill=new Bill();
        bill.setOrderId(order.getId());
        bill.setGenerateDate(order.getCreateDate());
        bill.setUserId(order.getPayUserId());
        bill.setId(UuidUtil.get32UUID());
        bill.setChannel(order.getChannel());
        String payType = order.getPayType();
        if(Const.PAY_SERVICE_RECHARGE.equals(payType)){
            bill.setType(Const.BILL_TYPE_INCOME);
        }
        //悬赏，打赏，转账
        if(Const.PAY_SERVICE_OFFER.equals(payType)||
                Const.PAY_SERVICE_REWARD.equals(payType)
                ||Const.PAY_SERVICE_TRANSFER.equals(payType)){
            bill.setType(Const.BILL_TYPE_PAID);
            Bill inComeBill=new Bill();
            inComeBill.setId(UuidUtil.get32UUID());
            inComeBill.setChannel(order.getChannel());
            inComeBill.setType(Const.BILL_TYPE_INCOME);
            inComeBill.setGenerateDate(order.getCreateDate());
            inComeBill.setUserId(order.getByPayUserId());
            inComeBill.setOrderId(order.getId());
            this.save(inComeBill);
        }
        this.save(bill);
    }
}
