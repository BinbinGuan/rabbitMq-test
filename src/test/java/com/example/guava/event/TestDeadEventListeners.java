package com.example.guava.event;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author: GuanBin
 * @date: Created in 下午3:32 2020/5/29
 */
public class TestDeadEventListeners {
    @Test
    public void testDeadEventListeners() throws Exception {

        EventBus eventBus = new EventBus("test");
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));

        System.out.println("deadEvent:"+deadEventListener.isNotDelivered());

    }
}
