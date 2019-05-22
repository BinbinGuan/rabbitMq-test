package com.example.exception;

import lombok.Getter;

/**
 * 多数情况下，创建自定义异常需要继承Exception，本例继承Exception的子类RuntimeException
 * @author: GuanBin
 * @date: Created in 上午11:39 2019/5/22
 */
@Getter
public class CustomerException extends RuntimeException {
    private String resultCode; //异常对应的返回码
    private String messageDescription; //异常对应的描述信息

    public CustomerException (){
        super();
    }
    public CustomerException (String massage){
        super(massage);
        messageDescription=massage;
    }


    public CustomerException (String messageDescription,Throwable cause){
        super(messageDescription,cause);
        this.messageDescription = messageDescription;

    }

    public CustomerException (String massage,String resultCode){
        super();
        this.resultCode=resultCode;
        this.messageDescription=massage;

    }




}
