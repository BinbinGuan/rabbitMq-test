package com.example.jvm.test5;

public class NotInitialization {

    public static void main(String[] args) {
        Super[] superClasses = new Super[10];
        Super aSuper = new Super();

        System.out.println(ConstClass.HELLO_BINGO);

//        System.out.println(SubClass.value);
        // SuperClass init!
    }
}
