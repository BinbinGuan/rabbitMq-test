//package com.example.saml;
//
//import com.github.ulisesbocchio.spring.boot.security.saml.configurer.ServiceProviderBuilder;
//import com.github.ulisesbocchio.spring.boot.security.saml.configurer.ServiceProviderConfigurerAdapter;
//
///**
// * @author: GuanBin
// * @date: Created in 上午9:43 2020/9/24
// */
//public class MyServiceProviderConfig  extends ServiceProviderConfigurerAdapter {
//    @Override
//    public void configure(ServiceProviderBuilder serviceProvider) throws Exception {
//        // @formatter:off
//        serviceProvider
//                .metadataGenerator() //(1)
//                .entityId("localhost-demo")
//                .and()
//                .sso() //(2)
//                .defaultSuccessURL("/home")
//                .idpSelectionPageURL("/idpselection")
//                .and()
//                .logout() //(3)
//                .defaultTargetURL("/")
//                .and()
//                .metadataManager() //(4)
//                .metadataLocations("classpath:/idp-ssocircle.xml")
//                .refreshCheckInterval(0)
//                .and()
//                .extendedMetadata() //(5)
//                .idpDiscoveryEnabled(true)
//                .and()
//                .keyManager() //(6)
//                .privateKeyDERLocation("classpath:/localhost.key.der")
//                .publicKeyPEMLocation("classpath:/localhost.cert");
//        // @formatter:on
//    }
//
//}
