package com.example.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author: GuanBin
 * @date: Created in 下午10:57 2020/5/31
 */
public class IntegerListener {
    private Integer lastMessage;

    @Subscribe
    public void listen(Integer integer) {
        lastMessage = integer;
        System.out.println("Message:"+lastMessage);
    }

    public Integer getLastMessage() {
        return lastMessage;
    }
}
