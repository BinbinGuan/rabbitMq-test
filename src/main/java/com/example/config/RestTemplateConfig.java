package com.example.config;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 下午5:53 2020/6/23
 */
@Configuration
public class RestTemplateConfig {
    private final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);

    @Bean
    @ConditionalOnMissingBean({RestOperations.class, RestTemplate.class})
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());

        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        Iterator<HttpMessageConverter<?>> iterator = messageConverters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> converter = iterator.next();
            if (converter instanceof StringHttpMessageConverter) {
                iterator.remove();
            }
        }
        messageConverters.add(new StringHttpMessageConverter(Charset.defaultCharset()));

        return restTemplate;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.rest")
    @ConditionalOnMissingBean({ClientHttpRequestFactory.class})
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        return httpComponentsClientHttpRequestFactory;
    }

    @Bean
    public HttpClient httpClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setSSLContext(sslContext());

        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));

        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager());

        return httpClientBuilder.build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.rest.pool")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        return new PoolingHttpClientConnectionManager(socketFactoryRegistry());
    }

    @Bean
    public SSLContext sslContext() {
        try {
            return new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            logger.error("init httpclient bean exception", e);
        }
        return null;
    }

    @Bean
    public Registry<ConnectionSocketFactory> socketFactoryRegistry() {
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext(),
                hostnameVerifier);

        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslConnectionSocketFactory).build();
    }

}
