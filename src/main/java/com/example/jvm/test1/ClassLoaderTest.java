package com.example.jvm.test1;

public class ClassLoaderTest {
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws ClassNotFoundException {

        Singleton singleton = Singleton.getInstance();

        System.out.println("counter1:" + singleton.getCounter1());

        System.out.println("counter2:" + singleton.getCounter2());

        System.out.println(Class.forName("java.lang.String").getClassLoader());

        System.out.println(Class.forName("com.example.jvm.test1.ClassLoaderTest").getClassLoader());

    }

}
