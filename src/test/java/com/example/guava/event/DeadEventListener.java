package com.example.guava.event;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author: GuanBin
 * @date: Created in 下午3:31 2020/5/29
 */
public class DeadEventListener {
    boolean notDelivered = false;

    @Subscribe
    public void listen(DeadEvent event) {

        notDelivered = true;
    }

    public boolean isNotDelivered() {
        return notDelivered;
    }
}
