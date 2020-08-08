package com.example.jvm.test2;

public class ClassLoaderTestCase {
    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader classLoader=ClassLoader.getSystemClassLoader();

        System.out.println("ClassLoader:"+classLoader);

        Class<?> testClass=classLoader.loadClass("com.example.jvm.test2.Test2");
        System.out.println("using class.forName('com.example.jvm.test2.Test2')");

        testClass=Class.forName("com.example.jvm.test2.Test2");

    }
}
