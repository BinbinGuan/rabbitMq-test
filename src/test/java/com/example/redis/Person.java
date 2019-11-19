package com.example.redis;

import java.io.*;

/**
 * @author: GuanBin
 * @date: Created in 上午11:22 2019/11/12
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 9114122003745124969L;
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
