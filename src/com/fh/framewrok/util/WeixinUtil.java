package com.fh.framewrok.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fh.entity.wx.AccessToken;
import com.fh.entity.wx.ErrorResp;
import com.fh.entity.wx.Oauth2AccessToken;
import com.fh.entity.wx.WxUserInfo;
import com.fh.framewrok.manager.MyX509TrustManager;
import org.slf4j.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by admin on 2017/11/1.
 */
public class WeixinUtil {

    private static org.slf4j.Logger logger=  LoggerFactory.getLogger(WeixinUtil.class);

    private static String GET_ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static String AUTHORIZE2_USERINOF_REDIRECT_URL="https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";//微信扫码授权跳转登录url

    private static String AUTHORIZE2_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    private static String  AUTHORIZE2_GET_USERINFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
        } catch (ConnectException ce) {
            logger.error("Weixin server connection timed out.");
        } catch (Exception e) {
            logger.error("https request error:{}", e);
        }
        return buffer.toString();
    }

    public static AccessToken getAccessToken(String appid, String appsecret){
        String requestUrl=GET_ACCESS_TOKEN_URL.replace("APPID",appid).replace("APPSECRET",appsecret);
        String obj = httpRequest(requestUrl, "GET", null);
        logger.info("AccessToken获取到的值为："+obj);
        AccessToken token=null;
        try{
            ObjectMapper mapper=new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            token = mapper.readValue(obj, AccessToken.class);
        }catch (Exception e){
            ErrorResp err= (ErrorResp) JSONObject.parse(obj);
            logger.error("获取token失败：\n errcode="+err.getErrcode()+"\n errmsg="+err.getErrmsg());
        }
        return token;
    }

    public static String getStartURLToGetCode(){
        String appid = WeChatProperty.getPropertyValue("wx.appid");
        String url=AUTHORIZE2_USERINOF_REDIRECT_URL.replace("APPID",appid);
        url=url.replace("STATE","jinshan");
        String redirectUrl="http://www.zjs666.cn/znzz/test";
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url=url.replace("REDIRECT_URI",redirectUrl);
        return url;
    }

    public static Oauth2AccessToken getOauth2AccessToken(String code) throws Exception {
        String appid = WeChatProperty.getPropertyValue("wx.appid");
        String secret=WeChatProperty.getPropertyValue("wx.appSecret");
        String url=AUTHORIZE2_ACCESS_TOKEN_URL.replace("APPID",appid);
        url= url.replace("SECRET",secret);
        url=url.replace("CODE",code);
        logger.info("\nurl=========:"+url);
        ObjectMapper mapper=new ObjectMapper();
        String obj  = httpRequest(url, "POST", "");
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        Oauth2AccessToken oauth2AccessToken= null;
        try {
            oauth2AccessToken = mapper.readValue(obj,Oauth2AccessToken.class);
        } catch (Exception e) {
            ErrorResp error= null;
            error = mapper.readValue(obj,ErrorResp.class);
            if(40163==error.getErrcode().intValue()){
                throw new Exception("code："+code+"已经只能使用一次");
            }
        }
        return oauth2AccessToken;
    }

    public static WxUserInfo Oauth2GetUserInfo(String accessToken,String openid){
        logger.info("AUTHORIZE2_GET_USERINFO_URL:"+AUTHORIZE2_GET_USERINFO_URL);
        String url=AUTHORIZE2_GET_USERINFO_URL.replace("ACCESS_TOKEN",accessToken);
        url=url.replace("OPENID",openid);
        String obj = httpRequest(url, "POST", "");
        logger.info("WxUserInfo获取到的值为："+obj);
        WxUserInfo userinfo=null;
        ObjectMapper mapper=new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        try {
            userinfo = mapper.readValue(obj, WxUserInfo.class);
        } catch (IOException e) {
            logger.info("WxUserInfo转换异常"+e.getMessage());
            e.printStackTrace();
        }
        return userinfo;
    }


}
