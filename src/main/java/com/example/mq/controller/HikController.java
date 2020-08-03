package com.example.mq.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author: GuanBin
 * @date: Created in 下午5:39 2020/6/23
 */
@Slf4j
@RestController
@RequestMapping("/hkvs")
public class HikController {

    @Autowired
    RestTemplate restTemplate;

    private final String approval = "/#/main/new-application/pendingApproval/PROVISION_BP/";


    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public boolean start(@RequestParam(value = "key", required = false) String key
            ,@RequestParam(value = "unid", required = false) String unid) {
        log.info("start to process");
        sendMessage("123456", "马奕超", "马奕超,陆挺8", "马奕超,陆挺8", "guanbin", "guanbin", "测试（7234132）", unid, "审批",key);
        return true;
    }

    @RequestMapping(value = "/end", method = RequestMethod.GET)
    public boolean end(@RequestParam(value = "key", required = false) String key
            ,@RequestParam(value = "unid", required = false) String unid) {
        log.info("start to process");
        sendMessage("123456", "马奕超", "", "", "guanbin", "guanbin", "测试（7234132）", unid, "结束",key);
        return true;
    }


    public void sendMessage(String approvalId, String proposer, String curdealer, String curdealerEn, String predealer, String predealerEn, String subject, String unid, String status,String key) {
        log.info("Start to send message to oa ,curdealer is {},predealer is {} approvalId is {} subject is {} unid is {},status is {}", curdealer, predealer, approvalId, subject, unid, status);
        String clientId = "256";
        String secret_key=key;
        String appname = "云管平台";
        String apiKey = "002000022";
        String cmpUrl = "http://127.0.0.1";
        log.info("Environment variables clientId:{}, appname:{}, apiKey:{}, cmpUrl:{}, secret_key:{}", clientId, appname, apiKey, cmpUrl, secret_key);
        if (StringUtils.isBlank(secret_key)) {
            log.warn("secret key is null");
        }
        String authString = String.format("%s:%s", clientId, secret_key);
        byte[] bytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(bytes);


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode params = mapper.createObjectNode();
        params.put("arrivetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        params.put("curdealer", curdealer);
        params.put("curdealerEn", curdealerEn);
        params.put("unid", unid);
        params.put("appname", appname);
        params.put("proposer", proposer);
        params.put("appnameEn", "Cloud Management Platform");
        params.put("arrivetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        params.put("curdealer", curdealer);
        params.put("curdealerEn", curdealerEn);
        params.put("predealer", predealer);
        params.put("predealerEn", predealerEn);
        params.put("subject", subject);
        params.put("subjectEn", subject);
        params.put("unid", unid);
        params.put("status", status);
        params.put("url", cmpUrl + approval + approvalId);

        String paramJsonStr = null;
        try {
            paramJsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(params);
        } catch (JsonProcessingException e) {
            log.error("");
        }
        log.info("param json of approval:{} is {}", approvalId, paramJsonStr);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("API_KEY", apiKey);
        headers.add("Authorization", "Basic " + authStringEnc);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("in0", paramJsonStr);
        HttpEntity<Map> requestEntity = new HttpEntity<>(param, headers);
        ResponseEntity<ResponseData> responseEntity = restTemplate.exchange("http://cloudapi.hikvision.com.cn/api/api-uat", HttpMethod.POST, requestEntity, ResponseData.class);
        System.out.println(responseEntity.getBody().getCode()+responseEntity.getBody().getData());
        log.info("End send message of approvalId:{} response is {}", approvalId, responseEntity.getBody());
    }


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public Response end(){

        ResponseEntity<Response> responseEntity = restTemplate.exchange("http://localhost:8082/hkvs/test", HttpMethod.GET, new HttpEntity<>(null, new HttpHeaders()), Response.class);
        Response body = responseEntity.getBody();
        return body;

    }



    @Data
   static class  Response{
//        public Response(String code, Map map) {
////            this.code = code;
////            this.map = map;
////        }

        private String code;
        private Map map;
    }
}
