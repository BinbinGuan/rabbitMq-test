package com.example.mq.controller;

import lombok.Data;

import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 下午6:44 2020/6/23
 */
@Data
public class ResponseData {

    public ResponseData() {
    }

    public ResponseData(String code, Map<String, Object> data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    String code;
    Map<String,Object> data;
    String message;
}
