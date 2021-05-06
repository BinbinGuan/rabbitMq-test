package com.example.copy;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 下午3:01 2021/4/19
 */
public class DeepCopyTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.setName("汉语词典");
        dictionary.setWords(new ArrayList<String>() {{
            add("你好");
            add("深拷贝");
        }});
        Dictionary dictionary2=null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(dictionary);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        dictionary2 = (Dictionary)objectInputStream.readObject();
        System.out.println(dictionary == dictionary2);
        dictionary2.getWords().add("新词语");
        System.out.println("dictionary: " + dictionary.toString());
        System.out.println("dictionary2: " + dictionary2.toString());
    }


    @Data
    static class Dictionary implements Cloneable, Serializable {
        private String name;
        private List<String> words;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
