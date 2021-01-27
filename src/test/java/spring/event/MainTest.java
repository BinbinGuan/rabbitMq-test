package spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigTest.class);
        EventPbulish eventPbulish = context.getBean(EventPbulish.class);
        eventPbulish.publish("zhangsan");
        context.close();
    }
}
