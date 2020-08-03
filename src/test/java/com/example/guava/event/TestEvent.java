package com.example.guava.event;

/**
 * @author: GuanBin
 * @date: Created in 下午2:58 2020/5/29
 */
public class TestEvent {
    private final int message;
    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
    }
    public int getMessage() {
        return message;
    }
}
