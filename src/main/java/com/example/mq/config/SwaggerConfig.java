//package com.example.mq.config;
//
//import com.google.common.base.Predicate;
//import com.google.common.base.Predicates;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.RequestHandler;
//import springfox.documentation.builders.ParameterBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.Parameter;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
///**
// * @author: GuanBin
// * @date: Created in 下午4:46 2018/8/14
// */
//@Configuration
//public class SwaggerConfig implements BeanDefinitionRegistryPostProcessor {
//    /**
//     * 包名字
//     */
//    final static String BASE   = "com.example.*";
//
//
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//
//        Map<String, List<String>> childPackageMap = null;
//        try {
//            childPackageMap = PackageUtil.getChildPackageMap(AUTO, MANUAL);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<String> commonChildPackages = childPackageMap.get("commonChildPackages");
//        List<String> autoOnlyChildPackages = childPackageMap.get("autoOnlyChildPackages");
//        List<String> manualOnlyChildPackages = childPackageMap.get("manualOnlyChildPackages");
//
//        for (int i = 0; i < commonChildPackages.size(); i++) {
//            beanFactory.registerSingleton(commonChildPackages.get(i), api(commonChildPackages.get(i)));
//        }
//        for (int i = 0; i < autoOnlyChildPackages.size(); i++) {
//            beanFactory.registerSingleton(autoOnlyChildPackages.get(i), api(autoOnlyChildPackages.get(i)));
//        }
//
//    }
//
//    public Docket api(String groupName) {
//
//        Parameter parameterAccessToken = new ParameterBuilder().name("access-token")
//                .description("Access token identifier").modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build();
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName(groupName).apiInfo(resInfo()).select()
//                // 对所有api进行监控
//                .apis(Predicates.or(RequestHandlerSelectors.basePackage(MANUAL + "." + groupName),
//                        RequestHandlerSelectors.basePackage(AUTO + "." + groupName)))
//                // 对所有路径进行监控
//                .paths(PathSelectors.any()).build();
//        docket.globalOperationParameters(Arrays.asList(new Parameter[] { parameterAccessToken }));
//        return docket;
//    }
//
//
//    private Predicate<RequestHandler> getApisOr(List<String> groupNames) {
//        List<Predicate<RequestHandler>> predicates = new ArrayList<>();
//        for (String groupName : groupNames) {
//            Predicate<RequestHandler> predicate = RequestHandlerSelectors.basePackage(MANUAL + "." + groupName);
//            predicates.add(predicate);
//        }
//        Predicate<RequestHandler>[] predicateArray = new Predicate[predicates.size()];
//        Predicate<RequestHandler> predicate = Predicates.or(predicates.toArray(predicateArray));
//        return predicate;
//    }
//
//    @Bean
//    public Docket export() {
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("外部对接").apiInfo(resInfo()).select()
//                // 对所有api进行监控
//                .apis(Predicates.or(RequestHandlerSelectors.basePackage("com.example")))
//                // 对所有路径进行监控
//                .paths(PathSelectors.any()).build();
//        docket.globalOperationParameters(Arrays.asList(new Parameter[] {  }));
//        return docket;
//    }
//
//    private ApiInfo resInfo() {
//        return new ApiInfo("javaApi", "java8-test Api汇总", "1.0", "http://www.guanbinbin.top",
//                new Contact("GuanBin", "http://www.guanbinbin.top", "983529954@qq.com"), "商业版本", "");
//    }
//
//}
