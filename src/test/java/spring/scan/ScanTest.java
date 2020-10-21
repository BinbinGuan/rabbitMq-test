package spring.scan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(Config.class);
        String[] definitionNames = applicationContext2.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}
