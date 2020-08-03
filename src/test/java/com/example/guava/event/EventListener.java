package com.example.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author: GuanBin
 * @date: Created in 下午2:59 2020/5/29
 */
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
