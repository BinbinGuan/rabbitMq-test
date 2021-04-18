package com.example.spi;

/**
 * @author: GuanBin
 * @date: Created in 下午10:31 2021/4/18
 */
public class SpiTest1ServiceImpl implements SpiService {

    @Override
    public void performTask() {
        System.out.println("test1 执行中");
    }
}
