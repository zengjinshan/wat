package com.fh.service.watermeter.user;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.fh.entity.watermeter.PhoneSsm;
import com.fh.entity.watermeter.WatUser;
import com.fh.framewrok.service.BaseService;
import com.fh.framewrok.util.AliSmsUtil;
import com.fh.framewrok.util.SexEnum;
import com.fh.framewrok.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by admin on 2018/2/9.
 */
@Service
public class WatUserService extends BaseService {

    private final static Logger log= LoggerFactory.getLogger(WatUserService.class);

    public final static String mapper="WatUserMapper.";

    public SendSmsResponse sendSsm(String telPhone)  throws Exception{
        String verification = AliSmsUtil.getVerification();//获取验证码
        SendSmsResponse sendSmsResponse = AliSmsUtil.sendSms(telPhone, verification);
        if(sendSmsResponse.getCode()!=null&&sendSmsResponse.getCode().equals("OK")){
            PhoneSsm ssm=new PhoneSsm();
            ssm.setId(UuidUtil.get32UUID());
            ssm.setPhone(telPhone);
            ssm.setCreateDate(new Date());
            ssm.setValidInd("1");
            ssm.setValidMsg(verification);
            this.save(ssm);
        }
        return sendSmsResponse;
    }
    public PhoneSsm findPhoneSsm(PhoneSsm ssm) throws Exception {
        PhoneSsm phoneSsm = (PhoneSsm) this.queryForObjectBySql(mapper + "findPhoneSsm", ssm);
        return phoneSsm;
    }
    public String generatWatNo() throws Exception {
        String watNo= (String) this.queryForObjectBySql(mapper+"generatWatNo",null);
        return "W"+watNo;
    }

    public void updateUser(WatUser user)throws Exception{
        this.updateBySql(mapper+"updateUser",user);
    }

    public void updateUserByPhone(String phone,String password) throws Exception {
        WatUser user=new WatUser();
        user.setPhone(phone);
        user.setPassword(password);
        this.updateBySql(mapper+"updateUserByPhone",user);
    }


}
