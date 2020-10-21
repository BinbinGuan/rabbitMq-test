//package com.example.thread;
//
//import com.github.ulisesbocchio.spring.boot.security.saml.bean.SAMLConfigurerBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author: GuanBin
// * @date: Created in 上午9:55 2020/9/24
// */
//public class MyServiceProviderConfig1 extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    SAMLConfigurerBean saml() {
//        return new SAMLConfigurerBean();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    //Needed in some cases to prevent infinite loop
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.parentAuthenticationManager(null);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http.httpBasic()
//                .disable()
//                .csrf()
//                .disable()
//                .anonymous()
//                .and()
//                .apply(saml())
//                .serviceProvider()
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
//                .publicKeyPEMLocation("classpath:/localhost.cert")
//                .http()
//                .authorizeRequests()
//                .requestMatchers(saml().endpointsMatcher())
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();
//        // @formatter:on
//    }
//}
