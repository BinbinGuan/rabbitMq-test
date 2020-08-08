package com.example.jvm.oom;

import java.util.Vector;

public class OomTest {
    public static void main(String[] args) {
        Vector v = new Vector();
        for (int i = 0; i < 25; i++) {
            v.add(new byte[1 * 1024 * 1024]);
        }
        System.out.printf(v.toString());
    }
}
