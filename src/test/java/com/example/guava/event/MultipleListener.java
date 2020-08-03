package com.example.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author: GuanBin
 * @date: Created in 下午3:19 2020/5/29
 */
public class MultipleListener {
    public Integer lastInteger;
    public Long lastLong;

    @Subscribe
    public void listenInteger(Integer event) {
        lastInteger = event;
        System.out.println("event Integer:"+lastInteger);
    }

    @Subscribe
    public void listenLong(Long event) {
        lastLong = event;
        System.out.println("event Long:"+lastLong);
    }

    public Integer getLastInteger() {
        return lastInteger;
    }

    public Long getLastLong() {
        return lastLong;
    }
}
