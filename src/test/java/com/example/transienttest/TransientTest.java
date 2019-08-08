package com.example.transienttest;

import java.io.*;

/**
 * @author: GuanBin
 * @date: Created in 下午2:11 2019/8/8
 */
public class TransientTest {

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("GuanBin");
        user.setPassword("123456");
        System.out.println("read before Serializable: ");
        System.out.println("username: " + user.getUsername());
        System.err.println("password: " + user.getPassword());

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/Users/guanbinbin/user.txt"));
            os.writeObject(user);//将user对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream("/Users/guanbinbin/user.txt"));
            user = (User) oi.readObject();
            oi.close();
            System.out.println("read after Serializable: ");
            System.out.println("username: " + user.getUsername());
            System.err.println("password: " + user.getPassword());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
