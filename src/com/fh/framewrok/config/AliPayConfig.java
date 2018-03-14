package com.fh.framewrok.config;

import com.fh.framewrok.util.FileUtil;
import org.apache.commons.io.FileUtils;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
public class AliPayConfig {

    // 1.商户appid
    public static String APPID = "2016091200490817";

    // 2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCV+BYwyDSqycbYZyZwLh9HiDBtLEavjD+a/u5DPnNCI2E7kWWY+mj8th2OW+OXFXhy7JZYIQyoZQ6biKMGfvkdrnKvMMzccvmclLkQn34W/ViBwigtKnJIoxExbXg1bYnKDHTf6YisYFhLa02ac9CnOg9y4QKhwsWrb6IzTCKyYTTf+4d9MNY9EBJUmMLhtz9asrer395b4mncyLSXktSfrIZEMgTkhrCk6GLB1ZJ9LzesEnOqtLgyuBEY22DOEPBu7R55zDqegfgIfV9wRrJr1O/W6JM0Qt4TCIKKnMeFYVAV2hRfoaPxB5TLIpqw3RV+mmQtML/X4rONNg7odHfTAgMBAAECggEBAJRsqpmxK970QKagwDw6iZ6kPTlvnq3TYogmUu2eezg0B+GKr0DOZOXn4Cyq6P74svMbEFk8rGhlTCXG/4JIXIlxw/N7muV7rWu5DchT8dkS+GvEGztITT9ylGuU6moGdV7RIUMSwL/Jwtkx4+Y/RXycS/nolFBAkp5vp9qZOaXMZfQ26cwF4uPWilLVkp7wu0bg2ocY+OLtYD+EV44IJtORIkSz7GP1WokHJlwJnWC471B6Mhaxs8qaBTlLzstBg/dKuFa+IFax2wMjAtB5JcfBjniPPSnXKrrKmckaukopkcvo2m5f6t9AF0m8ExUZDqo2E/CODwwxptSipO0bpaECgYEA1Pi/5OPW/2MF917qhC+EzRqK+SeZY9TtLAMiDRiBq0XLkOBERbKp7J0NHYXERUyBYXJQCGiT2wTZFD0mm+abV7qnv3xGDN8+p2egvINqFHC1xzdszUWhs5xzMjVlnl6eRllbcSAFl7PSKnQqMDF2MXidkfztcRDD/REa4WZbS7kCgYEAtES88ww4lmSbf5c/ukUHrW5bQLkiJOGRii23ZH5JZo9PrQ2kYO/RIutMZbBTadXCJal9EhyIFnZiFjEWiDSWWbehiAAIUUwJwWC3u9DpEH+470JW1PMWkwHesvcxGc12ESB5NqBmiBgg05hC8+wKx8BIj3HoIyP6fhldvTCiHesCgYBoCLNEjn/h9W9erYEF3WiUAJlOqKdnewX4b/dcSlJdZhTQypzPJz2WlgloGIYODBdPkNXDpcJERRP9rPmFQpszubxj78MQWqVyhU6hh9hz77L8g/ikNZWN9ajYJ/lh6XqLhu8CTdAtkOvjTSemryK7e94NCG68wqHap1ZoMOX8EQKBgQCmpbjrEswpM96QaBkwsGn1iURwo/UNlT1/ON4F170xQ6DLgQYJ2wlCN7WLeCLUfxiYyoNlv9yKr2vjygNjbQHk9J4uZKFLznhiPW/8/5bFet1SycgN2p9uzVa7qUrJ6KeSE6watNO0UmHeWi7i4b3hhT9O5GVOPw9tjTOcFx53ewKBgGITg9xZ25kmWGgJidlZ6pQUZCfdtHo3Wrbc+I6b4bj60o1CrchTmtg/FMh3ii800B9VBanPbdusK2iMDyCIysX01hNEh2eFBmbjK66EHbrb1/iBlCsn9Jl5cnK1Z9HfgDiWffjNwbLG2WfV5q1VHDP5Hgx9qYBk3T8PKZclEXZs";

    // 3.支付宝公钥
    public static  String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlfgWMMg0qsnG2GcmcC4fR4gwbSxGr4w/mv7uQz5zQiNhO5FlmPpo/LYdjlvjlxV4cuyWWCEMqGUOm4ijBn75Ha5yrzDM3HL5nJS5EJ9+Fv1YgcIoLSpySKMRMW14NW2Jygx03+mIrGBYS2tNmnPQpzoPcuECocLFq2+iM0wismE03/uHfTDWPRASVJjC4bc/WrK3q9/eW+Jp3Mi0l5LUn6yGRDIE5IawpOhiwdWSfS83rBJzqrS4MrgRGNtgzhDwbu0eecw6noH4CH1fcEaya9Tv1uiTNELeEwiCipzHhWFQFdoUX6Gj8QeUyyKasN0VfppkLTC/1+KzjTYO6HR30wIDAQAB";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String notify_url = "http://www.zjs666.cn/alipay/notifyUrl";

    // 5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static final String return_url = "http://www.xxx.com/alipay/return_url.do";

    // 6.请求网关地址
    public static final String URL = "https://openapi.alipaydev.com/gateway.do";

    public static final String METHOD="alipay.trade.app.pay";

    // 7.编码
    public static final String CHARSET = "utf-8";

    // 8.返回格式
    public static final String FORMAT = "JSON";

    // 9.加密类型
    public static final String SIGNTYPE = "RSA2";

    public static final String VERSION="1.0";

}
