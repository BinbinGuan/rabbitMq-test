package com.example.java8test.exception;

import com.example.exception.CustomerException;

/**
 * @author: GuanBin
 * @date: Created in 上午11:46 2019/5/22
 */
public class exceptionTest {

    public static void main(String[] args) {

        try {
            testException();
        } catch (CustomerException e) {
            e.printStackTrace();
            System.out.println("MsgDes\t" + e.getMessageDescription());
            System.out.println("RetCd\t" + e.getResultCode());
        }

    }

    public static void testException() throws CustomerException{

        throw new CustomerException("109", "String[] strs's length < 4");

    }



}
