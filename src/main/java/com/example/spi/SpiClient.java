package com.example.spi;

import sun.security.provider.ConfigFile;

import java.util.ServiceLoader;

/**
 * @author: GuanBin
 * @date: Created in 下午10:40 2021/4/18
 */
public class SpiClient {
    public static void main(String[] args) {
        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);
        for(SpiService spi:load){
            spi.performTask();
        }
    }
}
