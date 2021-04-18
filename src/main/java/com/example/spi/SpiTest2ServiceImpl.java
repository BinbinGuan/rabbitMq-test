package com.example.spi;

/**
 * @author: GuanBin
 * @date: Created in 下午10:37 2021/4/18
 */
public class SpiTest2ServiceImpl implements SpiService {
    @Override
    public void performTask() {
        System.out.println("test2 打印");
    }
}
