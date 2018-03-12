package com.fh.framewrok.util;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;

/**
 * Created by Administrator on 2016/12/22.
 */
public class WsClient {

    public static WsClient instance=null;
    public static synchronized WsClient getInstance(){
        if(instance==null){
            instance=new WsClient();
        }
        return instance;
    }

    public static void main(String[] args) {
        String url = "http://localhost:8088/FHADMIN/services/webserviceDemo?wsdl";
        RPCServiceClient serviceClient = null;//Rpc服务调用
        try {
            serviceClient = new RPCServiceClient();
            EndpointReference endpointReference = new EndpointReference(url);
            Options options = serviceClient.getOptions();
            options.setTo(endpointReference);
            options.setTimeOutInMilliSeconds((long) 30000);//设置超时时间
            Object[] params = new Object[]{"1"};
            Class[] classes = new Class[]{String.class};
            QName qName = new QName("http://webservice.framewrok.fh.com", "demoFun");//命名空间地址，跟方法
            Object[] objects = serviceClient.invokeBlocking(qName, params, classes);
            String result = objects[0].toString();
            serviceClient.cleanupTransport();
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
    }
}
