package com.example.transienttest;

import java.io.*;

/**
 * @author: GuanBin
 * @date: Created in 下午3:06 2019/8/8
 */
public class ExternalizableTest implements Externalizable {

    private transient String content = "不管是否是被transient修饰都可以序列化";

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(content);//序列化到文件中的属性，需要手动加进去
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        content = (String) in.readObject();
    }

    public static void main(String[] args) {
        ExternalizableTest externalizableTest = new ExternalizableTest();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test"));
            out.writeObject(externalizableTest);
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("test"));
            externalizableTest = (ExternalizableTest) input.readObject();
            System.out.println(externalizableTest.content);
            out.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
