package com.fh.controller.watermeter;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.fh.entity.vo.*;
import com.fh.entity.watermeter.*;
import com.fh.framewrok.response.Token;
import com.fh.framewrok.response.WatResponse;
import com.fh.framewrok.util.*;
import com.fh.service.watermeter.comment.CommentService;
import com.fh.service.watermeter.complaint.ComplaintService;
import com.fh.service.watermeter.information.InformationService;
import com.fh.service.watermeter.liked.PraiseService;
import com.fh.service.watermeter.user.WatUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * Created by admin on 2018/2/9.
 */
@Controller
@RequestMapping(value = "/wat/user")
public class WatLoginController {

    private final static Logger log = LoggerFactory.getLogger(WatLoginController.class);

    @Autowired
    private WatUserService userService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private PraiseService praiseService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ComplaintService complaintService;

    @RequestMapping(value = "sendSsm")
    @ResponseBody
    public WatResponse sendSsm(@RequestParam(value = "telPhone", required = false) String telPhone) throws Exception {
        WatResponse response = new WatResponse();
        boolean phoneLegal = MobileUtil.isPhoneLegal(telPhone);
        if (phoneLegal) {
            try {
                SendSmsResponse sendSmsResponse = userService.sendSsm(telPhone);
                if (!"OK".equals(sendSmsResponse.getCode())) {
                    response.setStatus(Const.RESPONSE_STATUS_FAILED);
                    response.setMsg("发送失败,错误代码:" + sendSmsResponse.getCode() + ",错误描述：" + sendSmsResponse.getMessage());
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                response.setMsg("发送失败,系统错误");
            }
            response.setMsg("发送成功");
        } else {
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,手机格式不正确");
        }
        return response;
    }


    @RequestMapping(value = "regist")
    @ResponseBody
    public WatResponse regist(@RequestParam(value = "telPhone", required = false) String telPhone,
                              @RequestParam(value = "validCode", required = false) String validCode,
                              @RequestParam(value = "password", required = false) String password) {
        WatResponse response = new WatResponse();
        boolean phoneLegal = MobileUtil.isPhoneLegal(telPhone);
        if (password.contains(" ")) {
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("密码不允许带有空格");
            return response;
        }
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("phone", telPhone);
            int i = userService.countByFields(WatUser.class, map);
            if (i > 0) {
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                response.setMsg("手机号码已经被注册");
                return response;
            }
            if (phoneLegal) {
                PhoneSsm ssm = new PhoneSsm();
                ssm.setValidMsg(validCode);
                ssm.setValidInd("1");
                ssm.setPhone(telPhone);
                PhoneSsm phoneSsm = userService.findPhoneSsm(ssm);
                if (phoneSsm != null) {
                    long time = phoneSsm.getCreateDate().getTime();
                    long time2 = new Date().getTime();
                    if ((time2 - time) / 1000 > 120) {
                        response.setStatus(Const.RESPONSE_STATUS_FAILED);
                        response.setMsg("验证码失效,请重新发送");
                        phoneSsm.setValidInd("0");
                        userService.update(phoneSsm);
                    } else {
                        WatUser user = new WatUser();
                        user.setId(UuidUtil.get32UUID());
                        user.setPhone(telPhone);
                        user.setPassword(MD5.md5(password));
                        user.setUsername(telPhone);
                        user.setValidInd("1");
                        user.setSex(SexEnum.WOMEN.getValue());//女生
                        String watNo = userService.generatWatNo();
                        user.setWatNo(watNo);
                        user.setNickName(watNo);
                        user.setCreateDate(new Date());
                        user.setImg(Const.DEFAULT_IMG);
                        String qrCodeName = UuidUtil.get32UUID() + ".png";
                        String filepath = WaterProperty.getPropertyValue("filepath")+qrCodeName;
                        // String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + qrCodeName;  //存放路径
                        TwoDimensionCode.encoderQRCode(telPhone, filepath, "png");
                      /*  File srcFile=new File(filePath);
                        File destFile=new File(filepath+qrCodeName);
                        FileUtils.moveFile(srcFile,destFile);*///移动上传文件到tomcat映射文件目录
                        user.setQrCodeImg(Const.DEFAULT_PATH + qrCodeName);
                        userService.save(user);
                        response.setMsg("注册成功！");
                        phoneSsm.setValidInd("0");
                        userService.update(phoneSsm);
                    }
                } else {
                    response.setStatus(Const.RESPONSE_STATUS_FAILED);
                    response.setMsg("验证失败,请重新发送");
                }
            } else {
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                response.setMsg("发送失败,手机格式不正确");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "login")
    @ResponseBody
    public WatResponse login(@RequestParam(value = "telPhone", required = false) String telPhone,
                             @RequestParam(value = "password", required = false) String password) {
        WatResponse response = new WatResponse();
        try {
            boolean phoneLegal = MobileUtil.isPhoneLegal(telPhone);
            if (password.contains(" ")) {
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                response.setMsg("密码不允许带有空格");
                return response;
            }
            if (phoneLegal) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("phone", telPhone);
                map.put("password", MD5.md5(password));
                map.put("validInd", "1");
                WatUser user = (WatUser) userService.findObjectByFields(WatUser.class, map);
                if (user != null) {
                    if (SexEnum.MAN.getValue().equals(user.getSex())) {
                        user.setSex(SexEnum.MAN.getName());
                    }
                    if (SexEnum.WOMEN.getValue().equals(user.getSex())) {
                        user.setSex(SexEnum.WOMEN.getName());
                    }
                    Token token = UserInfoCache.putToken(user);
                    response.setObj(token);
                    response.setMsg("登录成功");
                    response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
                } else {
                    response.setStatus(Const.RESPONSE_STATUS_FAILED);
                    response.setMsg("用户名或密码错误");
                }
            } else {
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                response.setMsg("发送失败,手机格式不正确");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "updatePassword")
    @ResponseBody
    public WatResponse updatePassword(@RequestParam(value = "telPhone", required = false) String telPhone,
                                      @RequestParam(value = "validCode", required = false) String validCode,
                                      @RequestParam(value = "password", required = false) String password) {
        WatResponse response = new WatResponse();
        boolean phoneLegal = MobileUtil.isPhoneLegal(telPhone);
        if (password.contains(" ")) {
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("密码不允许带有空格");
            return response;
        }
        if (phoneLegal) {
            PhoneSsm ssm = new PhoneSsm();
            ssm.setValidMsg(validCode);
            ssm.setValidInd("1");
            ssm.setPhone(telPhone);
            try {
                PhoneSsm phoneSsm = userService.findPhoneSsm(ssm);
                if (phoneSsm != null) {
                    long time = phoneSsm.getCreateDate().getTime();
                    long time2 = new Date().getTime();
                    if ((time2 - time) / 1000 > 120) {
                        response.setStatus(Const.RESPONSE_STATUS_FAILED);
                        response.setMsg("验证码失效,请重新发送");
                        phoneSsm.setValidInd("0");
                        userService.update(phoneSsm);
                    } else {
                        WatUser user = new WatUser();
                        user.setPhone(telPhone);
                        userService.updateUserByPhone(telPhone, MD5.md5(password));
                        phoneSsm.setValidInd("0");
                        userService.update(phoneSsm);
                        response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
                        response.setMsg("修改成功");
                    }
                } else {
                    response.setStatus(Const.RESPONSE_STATUS_FAILED);
                    response.setMsg("验证失败,请重新发送");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                response.setMsg("发送失败,系统错误");
            }
        } else {
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,手机格式不正确");
        }
        return response;
    }

    @RequestMapping(value = "editNick")
    @ResponseBody
    public WatResponse editNick(@RequestParam("kId") String kId, @RequestParam(value = "nickName", required = false) String nickName) {
        Token token = UserInfoCache.getToken(kId);
        WatResponse response = new WatResponse();
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        if (StringUtils.isBlank(nickName)) {
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("昵称不能为空");
            return response;
        }
        WatUser user = token.getUser();
        user.setNickName(nickName);
        try {
            userService.updateUser(user);
            token.setUser(user);
            UserInfoCache.updateToken(token);
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setMsg("修改成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "editTag")
    @ResponseBody
    public WatResponse editTag(@RequestParam("kId") String kId, @RequestParam(value = "tag", required = false) String tag) {
        Token token = UserInfoCache.getToken(kId);
        WatResponse response = new WatResponse();
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        user.setTag(tag);
        try {
            userService.updateUser(user);
            token.setUser(user);
            UserInfoCache.updateToken(token);
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setMsg("修改成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "editSex")
    @ResponseBody
    public WatResponse editSex(@RequestParam("kId") String kId, @RequestParam(value = "sex", required = false) String sex) {
        Token token = UserInfoCache.getToken(kId);
        WatResponse response = new WatResponse();
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        user.setSex(sex);
        try {
            userService.updateUser(user);
            token.setUser(user);
            UserInfoCache.updateToken(token);
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setMsg("修改成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public WatResponse upload(@RequestParam("file") MultipartFile files, HttpServletRequest request,
                              @RequestParam("kId") String kId) {
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        if (null != files && !files.isEmpty()) {
            try {
                String filepath = WaterProperty.getPropertyValue("filepath");
                String fileName = FileUpload.fileUp(files, filepath, UuidUtil.get32UUID());
                user.setImg(Const.DEFAULT_PATH + fileName);
                userService.updateUser(user);
                token.setUser(user);
                UserInfoCache.updateToken(token);
                response.setMsg("上传成功");
                response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
                response.setObj(filepath + fileName);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                response.setMsg("发送失败,系统错误");
            }
        }
        return response;
    }

    @RequestMapping(value = "infoList")
    @ResponseBody
    public WatResponse infoList(@RequestParam("kId") String kId,
                                @RequestParam(value = "time", required = false) String lastTime) {
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        String userId = user.getId();
        try {
            List<ShieldUser> shieldUsers = userService.findListByField(ShieldUser.class, "userId", userId);
            Map<String, Object> map = new HashMap<String, Object>();
            String shields = "";
            String infoIds = "";
            if (CollectionUtils.isNotEmpty(shieldUsers)) {
                for (int i = 0; i < shieldUsers.size(); i++) {
                    if(shieldUsers.get(i).getType().equals("1")){
                        shields = shields + shieldUsers.get(i).getShielderId() + ",";
                    }
                    if(shieldUsers.get(i).getType().equals("2")){
                        infoIds = infoIds + shieldUsers.get(i).getShielderId() + ",";
                    }
                }
                map.put("shields", shields);
                map.put("infoIds",infoIds);
            }
            if (StringUtils.isNotEmpty(lastTime)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                Date date = format.parse(lastTime);
                map.put("lastTime", date);
            }
            map.put("visible",1);
            List<InformationVo> infos = informationService.findInfo(map);
            //与我相关
            Map<String,Object> m=new HashMap<String,Object>();
            Integer count = commentService.countRelateToMe(user);
            m.put("relateToMeCount",count);
            m.put("infos",infos);
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setObj(m);
            response.setMsg("查询成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "appear")
    @ResponseBody
    public WatResponse appear(@RequestParam(value = "files",required = false) MultipartFile[] files,
                              @RequestParam(value = "content", required = false) String content,
                              @RequestParam(value = "kId", required = false) String kId,
                              @RequestParam(value = "infoType") String infoType,
                              @RequestParam(value = "offerMoney", required = false) String offerMoney,
                              @RequestParam(value = "offerTime", required = false) String offerTime,
                              @RequestParam(value = "phoneName",required = false,defaultValue = "") String phoneName
    ) {
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try {
            if (StringUtils.isEmpty(content) && files == null) {
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                response.setMsg("内容不能为空！");
                return response;
            }
            String contentType = "";
            String originalFilename="";
            String fileType="";
            if(files.length==0){
                contentType="1";
            }else{
                for(int i=0;i<files.length;i++){
                    originalFilename = files[i].getOriginalFilename();
                    fileType = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
                    if (fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("jpg")
                            || fileType.equalsIgnoreCase("bmp") || fileType.equalsIgnoreCase("jpeg")
                            || fileType.equalsIgnoreCase("gif")) {
                        if(StringUtils.isEmpty(contentType)){
                            contentType = "2";
                        }else{
                            if(contentType.equals("2")){
                                contentType = "2";
                            }else {
                                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                                response.setMsg("不支持不同多媒体类型文件上传");
                                return response;
                            }
                        }
                    } else if (fileType.equalsIgnoreCase("mp3") || fileType.equalsIgnoreCase("wav") ||
                            fileType.equalsIgnoreCase("wma") || fileType.equalsIgnoreCase("ogg")) {
                        if(StringUtils.isEmpty(contentType)){
                            contentType = "4";
                        }else{
                            if(contentType.equals("4")){
                                contentType = "4";
                            }else {
                                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                                response.setMsg("不支持不同多媒体类型文件上传");
                                return response;
                            }
                        }
                    } else if (fileType.equalsIgnoreCase("mp4") || fileType.equalsIgnoreCase("3gp")
                            || fileType.equalsIgnoreCase("wmv") || fileType.equalsIgnoreCase("avi") ||
                            fileType.equalsIgnoreCase("rmvb")) {
                        if(StringUtils.isEmpty(contentType)){
                            contentType = "3";
                        }else{
                            if(contentType.equals("3")){
                                contentType = "3";
                            }else {
                                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                                response.setMsg("不支持不同多媒体类型文件上传");
                                return response;
                            }
                        }
                    } else {
                        response.setStatus(Const.RESPONSE_STATUS_FAILED);
                        response.setMsg("不支持"+fileType+"该文件格式上传");
                        return response;
                    }
                }
            }

            Information info = new Information();
            info.setId(UuidUtil.get32UUID());
            info.setContent(content);
            info.setCreateDate(new Date());
            info.setCommentNum(0);
            info.setForwardInfo("");
            info.setContentType(contentType);
            info.setForwardNum(0);
            info.setInfoType(infoType);
            info.setNickName(user.getNickName());
            info.setOfferMoney(offerMoney);
            if (!StringUtils.isEmpty(offerTime)) {
                info.setOfferTime(Integer.parseInt(offerTime));
            }
            info.setPraiseNum(0);
            info.setShieldNum(0);
            info.setTag(user.getTag());
            info.setUserId(user.getId());
            info.setUserImg(user.getImg());
            info.setForwardInd("0");
            info.setPhoneName(phoneName);
            informationService.save(info);

            for(int i=0;i<files.length;i++){
                originalFilename = files[i].getOriginalFilename();
                fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
                String filepath = WaterProperty.getPropertyValue("filepath");
                String fileName = FileUpload.fileUp(files[i], filepath, UuidUtil.get32UUID());
                Attachment atta = new Attachment();
                atta.setCreateDate(new Date());
                atta.setId(UuidUtil.get32UUID());
                atta.setServiceId(info.getId());
                atta.setServiceType("1");
                atta.setFilePath(Const.DEFAULT_PATH + fileName);
                atta.setType(fileType);
                informationService.save(atta);
            }
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setMsg("发布成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }


    @RequestMapping(value = "praise")
    @ResponseBody
    public WatResponse praise(@RequestParam("kId") String kId,
                             @RequestParam("infoId") String infoId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            List<PraiseVo> praiseList = praiseService.findPraiseByUserAndInfo(user, infoId);
            if(praiseList!=null&&praiseList.size()>0){
                response.setMsg("已结点赞过");
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
            }else {
                Integer num = praiseService.savePraise(user, infoId);
                response.setMsg("点赞成功");
                response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
                response.setObj(num);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "praiseList")
    @ResponseBody
    public WatResponse praiseList(@RequestParam("kId") String kId,
                                  @RequestParam("infoId") String infoId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            List<PraiseVo> praiseList = praiseService.findPraiseByUserAndInfo(user, infoId);
            response.setMsg("请求成功");
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setObj(praiseList);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "comment")
    @ResponseBody
    public WatResponse comment(@RequestParam("kId") String kId,
                               @RequestParam("infoId") String infoId,
                               @RequestParam("content") String content){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            commentService.comment(user,infoId,content);
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setMsg("评论成功");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "commentList")
    @ResponseBody
    public WatResponse commentList(@RequestParam("kId") String kId,
                               @RequestParam("infoId") String infoId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        try{
            List<CommentVo> commentVos = commentService.commentList(infoId);
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setMsg("请求成功");
            response.setObj(commentVos);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "relateToMe")
    @ResponseBody
    public WatResponse relateToMe(@RequestParam("kId") String kId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            List list = commentService.relateToMe(user);
            RelateToMe relate=new RelateToMe();
            relate.setUserId(user.getId());
            relate.setCreateDate(new Date());
            relate.setId(UuidUtil.get32UUID());
            commentService.save(relate);
            response.setMsg("查询成功");
            response.setObj(list);
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "infoDetail")
    @ResponseBody
    public WatResponse infoById(@RequestParam("kId") String kId,@RequestParam("infoId") String infoId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            InfoDetail infoDetail = informationService.infoDetail(infoId,user);
            if(infoDetail!=null){
                response.setMsg("查询成功");
                response.setObj(infoDetail);
                response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            }else {
                response.setMsg("查询失败，无该消息");
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }


    @RequestMapping(value = "shield")
    @ResponseBody
    public WatResponse shield(@RequestParam("kId") String kId,@RequestParam("shieldId") String shieldId,
                              @RequestParam("type") String type){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            ShieldUser shield = new ShieldUser();
            shield.setId(UuidUtil.get32UUID());
            shield.setUserId(user.getId());
            shield.setShielderId(shieldId);
            shield.setType(type);
            informationService.save(shield);
            if(type.equals("2")){
                Information info = (Information) informationService.find(Information.class, shieldId);
                if(info!=null){
                    info.setShieldNum(info.getShieldNum()+1);
                    informationService.update(info);
                }
            }
            response.setMsg("屏蔽成功");
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "album")
    @ResponseBody
    public WatResponse album(@RequestParam("kId") String kId,@RequestParam("pageNum") Integer pageNum,
                               @RequestParam(value = "userId") String userId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            List<MyAlbumVo> myAlbumVos = informationService.myAlbumVoList(user, pageNum,userId);
            response.setMsg("查询成功");
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setObj(myAlbumVos);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "deleteInfo")
    @ResponseBody
    public WatResponse deleteInfo(@RequestParam("kId") String kId,@RequestParam("infoId") String infoId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            informationService.deleteInfo(user,infoId);
            response.setMsg("删除成功");
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "setVisible")
    @ResponseBody
    public WatResponse setVisible(@RequestParam("kId") String kId,@RequestParam("infoId") String infoId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            informationService.setVisible(user,infoId);
            response.setMsg("设置成功");
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "createGroup")
    @ResponseBody
    public WatResponse createGroup(@RequestParam("kId") String kId,
                                   @RequestParam("name") String name){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            if(StringUtils.isEmpty(name)){
                response.setStatus(Const.RESPONSE_STATUS_FAILED);
                response.setMsg("分组名称不能为空");
            }else {
                Group group=new Group();
                group.setId(UuidUtil.get32UUID());
                group.setCreateDate(new Date());
                group.setUserId(user.getId());
                group.setName(name);
                informationService.save(group);
                response.setMsg("设置成功");
                response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "groupList")
    @ResponseBody
    public WatResponse groupList(@RequestParam("kId") String kId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            List<Group> groupList = informationService.findListByField(Group.class, "userId", user.getId());
            if(groupList==null){
                groupList=new ArrayList<>();
            }
            response.setMsg("查询成功");
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setObj(groupList);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "complaint")
    @ResponseBody
    public WatResponse complaint(@RequestParam("infoId") String infoId,@RequestParam("kId") String kId,
                                 @RequestParam("content") String content,@RequestParam("files") MultipartFile[] files,
                                 @RequestParam(value = "link",required = false,defaultValue = "") String link,
                                 @RequestParam("type") String type){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try {
            complaintService.complaint(user,type,files,content,link,infoId);
            response.setMsg("投诉成功，我们会尽快审核");
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "myOfferInfo")
    @ResponseBody
    public WatResponse myOfferInfo(@RequestParam("kId") String kId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            Integer count = informationService.findThisMonthInfoCount(user);
            int offerCount= Integer.parseInt(WaterProperty.getPropertyValue("offer_count"))-count;
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("offerCount",offerCount);
            List<InformationVo> infos = informationService.findOfferInformation(user);
            map.put("infos",infos);
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setObj(map);
            response.setMsg("请求成功");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }

    @RequestMapping(value = "adopt")
    @ResponseBody
    public WatResponse adopt(@RequestParam("kId") String kId,@RequestParam("commentId") String commentId,
                             @RequestParam("infoId") String infoId){
        WatResponse response = new WatResponse();
        Token token = UserInfoCache.getToken(kId);
        if (token == null) {
            response.setStatus(Const.RESPONSE_STATUS_0);
            response.setMsg("token过期，请重新登录");
            return response;
        }
        WatUser user = token.getUser();
        try{
            Information info = (Information) informationService.find(Information.class, infoId);
            info.setAdoptCommentId(commentId);
            informationService.update(info);
            response.setStatus(Const.RESPONSE_STATUS_SUCCESS);
            response.setMsg("采纳成功");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            response.setStatus(Const.RESPONSE_STATUS_FAILED);
            response.setMsg("发送失败,系统错误");
        }
        return response;
    }





}
