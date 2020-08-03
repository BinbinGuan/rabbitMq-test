package com.example.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author: GuanBin
 * @date: Created in 下午10:54 2020/5/31
 */
public class NumberListener {
    private Number lastMessage;

    @Subscribe
    public void listen(Number integer) {
        lastMessage = integer;
        System.out.println("Message:"+lastMessage);
    }

    public Number getLastMessage() {
        return lastMessage;
    }
}
