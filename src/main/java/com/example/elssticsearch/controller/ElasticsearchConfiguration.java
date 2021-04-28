//package com.example.elssticsearch.controller;
//
///**
// * @author: GuanBin
// * @date: Created in 下午3:47 2021/3/31
// */
//
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//@EnableElasticsearchRepositories
//public class ElasticsearchConfiguration {
//
//    private final RestHighLevelClient restHighLevelClient;
//
//    @Autowired
//    public ElasticsearchConfiguration(RestHighLevelClient restHighLevelClient) {
//        RestClientBuilder builder = RestClient.builder(hosts);
//        builder.setHttpClientConfigCallback(callback -> callback.setDefaultCredentialsProvider(credentialsProvider)
//                .setKeepAliveStrategy((response, context) -> KEEP_ALIVE_MS));
//        this.restHighLevelClient = restHighLevelClient;
//    }
//
//    public static RestHighLevelClient createRestHighLevelClient(String esUrl, Long keepAlive) {
//        RestClientBuilder clientBuilder = RestClient.builder(createHttpHost(URI.create(esUrl)))
//                .setHttpClientConfigCallback(requestConfig -> requestConfig.setKeepAliveStrategy(
//                        (response, context) -> keepAlive));
//        return new RestHighLevelClient(clientBuilder);
//    }
//
//    private static HttpHost createHttpHost(URI uri) {
//        if (StringUtils.isEmpty(uri.getUserInfo())) {
//            return HttpHost.create(uri.toString());
//        }
//        try {
//            return HttpHost.create(new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), uri.getPath(),
//                    uri.getQuery(), uri.getFragment()).toString());
//        } catch (URISyntaxException ex) {
//            throw new IllegalStateException(ex);
//        }
//    }
//
//    @Bean
//    public ElasticsearchRestTemplate elasticsearchTemplate() {
//        return new ElasticsearchRestTemplate(restHighLevelClient);
//    }
//
//}
