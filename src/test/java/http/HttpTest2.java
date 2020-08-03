package http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 上午10:06 2020/5/15
 */
public class HttpTest2 {

    @Test
    public void keepAlive() throws Exception {

        for(int i=0;i<2000;i++){
            HttpClient httpClient = getHttpClient(30000);
            String url="http://192.168.12.12:80 /bigscreen/bigscreen/hosts/stats/test?tenantId=default";
            HttpClientGet(httpClient,url);
            Thread.sleep(200);
        }
    }

    private HttpClient getHttpClient(int timeout) {

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(100);
        connectionManager.setValidateAfterInactivity(2000);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout) //服务器返回数据(response)的时间，超过抛出read timeout
                .setConnectTimeout(timeout) //连接上服务器(握手成功)的时间，超出抛出connect timeout
                .setConnectionRequestTimeout(timeout)//从连接池中获取连接的超时时间，超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
                .build();

      return   HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();

    }

    private void HttpClientGet(HttpClient httpclient, String url ) throws Exception  {
        HttpGet httpGet = new HttpGet(url);
        List<NameValuePair> nvps = new ArrayList<>();
        HttpResponse response2 = httpclient.execute(httpGet);
        try {
            HttpEntity entity2 = response2.getEntity();
            System.out.println(EntityUtils.toString(entity2));
        } finally {
        }
    }
}
