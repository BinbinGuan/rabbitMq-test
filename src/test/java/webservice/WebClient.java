package webservice;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import java.net.URL;

/**
 * @author: GuanBin
 * @date: Created in 上午9:57 2020/5/19
 */
public class WebClient {
    public static void main(String[] args) {
        try {
            String call_12= "{“我是入参报文数据” }";

            String ress = "";

            Service service = new Service();


            Call call;

            call = (org.apache.axis.client.Call) service.createCall();

            //注意下面的地址是服务端提供的客户端访问的wsdl地址，但是不包含？wsdl部分的

            call.setTargetEndpointAddress(new URL("http://toa.htd.cn//services/HrmService?WSDL®"));
            call.setOperationName("getHrmSubcompanyInfo");

            call.setUseSOAPAction(true);

            ress= (String) call.invoke(new Object[] { "58.32.10.46" });
            System.out.println(ress);
        }catch (Exception e){

        }


    }
}
