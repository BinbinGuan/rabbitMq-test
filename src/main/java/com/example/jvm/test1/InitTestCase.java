package com.example.jvm.test1;

public class InitTestCase {
    static{

        System.out.println("InitTestCase's static block");

    }

    public static void main(String[] args) {

        Parent parent;

        System.out.println("===== split line =====");

        parent=new Child();

        System.out.println(Parent.a);

        System.out.println(Child.b);

    }
}
