package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
        ctx.register();
    }
}
