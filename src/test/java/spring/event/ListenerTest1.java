package spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerTest1  implements ApplicationListener<EventTest> {
    public void onApplicationEvent(EventTest event) {
        System.out.println("test1:" + event.getMessage());
    }
}
