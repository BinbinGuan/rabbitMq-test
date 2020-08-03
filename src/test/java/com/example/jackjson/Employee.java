package com.example.jackjson;

import lombok.Data;

/**
 * @author: GuanBin
 * @date: Created in 上午10:18 2020/6/15
 */
@Data
public class Employee {

    public Employee() {
    }

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    String name;

    String email;
}
