package com.example.jvm.test4;

public class ClassLoaderTest2 {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTest2.class.getClassLoader();    //获得加载ClassLoaderTest.class这个类的类加载器
        while(loader != null) {
            System.out.println(loader);
            loader = loader.getParent();    //获得父类加载器的引用
        }
        System.out.println(loader);
    }
}
