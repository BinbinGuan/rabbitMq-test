package com.example.guava.event;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author: GuanBin
 * @date: Created in 下午3:00 2020/5/29
 */
public class TestEventBus {
    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:"+listener.getLastMessage());

    }
}
