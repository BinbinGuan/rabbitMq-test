package http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 下午6:30 2020/4/24
 */
public class HttpClientTest {

    @Test
// 测试长连接问题
    public void keepAlive() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("id", "id");
        HttpClient httpClient = getHttpClient(30000);
        HttpClientPost(httpClient, "http://127.0.0.1:6500/test", "utf-8", params);
        HttpClientPost(httpClient, "http://127.0.0.1:6500/test", "utf-8", params);
    }

    private HttpClient getHttpClient(int timeout) {
        RequestConfig.Builder requestBuilder = RequestConfig.custom();
        requestBuilder = requestBuilder.setConnectTimeout(timeout);
        requestBuilder = requestBuilder.setSocketTimeout(timeout);
        requestBuilder = requestBuilder.setConnectionRequestTimeout(timeout);

        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setDefaultRequestConfig(requestBuilder.build()).setRetryHandler(new DefaultHttpRequestRetryHandler(3, true)).build();
        builder.setDefaultRequestConfig(requestBuilder.build()).setRetryHandler((exception, executionCount, context) -> {
            if (executionCount > 3) {
                System.out.println("Maximum tries reached for client http pool ");
                return false;
            }
            if (exception instanceof org.apache.http.NoHttpResponseException) {
                System.out.println("No response from server on " + executionCount + " call");
                return true;
            }
            return false;
        }).build();
        return builder.build();
    }

    private void HttpClientPost(HttpClient httpclient, String url , String charset , Map<String, String> map) throws Exception  {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> obj : map.entrySet()) {
            nvps.add(new BasicNameValuePair(obj.getKey() , obj.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        HttpResponse response2 = httpclient.execute(httpPost);
        try {
            HttpEntity entity2 = response2.getEntity();
            System.out.println(EntityUtils.toString(entity2));
        } finally {
        }
    }
}
