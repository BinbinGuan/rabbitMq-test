package com.example.mq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

/**
 * @author: GuanBin
 * @date: Created in 下午4:42 2019/4/16
 */
@RestController
@RequestMapping(value = "/api/mail")
@Slf4j
@Api(tags = "邮件测试API", value = "邮件测试API", description = "邮件测试API", position = 1)
public class MailService {


    @RequestMapping(method = RequestMethod.GET, value = "/test")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "测试apo", notes = "")
    public void testMail(@RequestParam(name = "SMTP_HOST", required = false) String smtpHost,
                         @RequestParam(name = "SMTP_PORT", required = false) String smtpPort,
                         @RequestParam(name = "SMTP_SSL_ENABLE", required = false) String smtpSslEnable,
                         @RequestParam(name = "SMTP_FROM", required = false) String smtpFrom,
                         @RequestParam(name = "toAddresses", required = false) String toAddresses,
                         @RequestParam(name = "username", required = false) String username,
                         @RequestParam(name = "password", required = false) String password
    ) {
        log.info("start to send email,smtpHost is {} ,smtpPort is {},smtpFrom is {},toAddress is {},username is {},password is {}", smtpHost, smtpPort, smtpFrom, toAddresses, username, password);
        Properties p = new Properties();
        p.put("SMTP_HOST", smtpHost);
        p.put("SMTP_PORT", smtpPort);
        p.put("SMTP_SSL_ENABLE", smtpSslEnable);
        p.put("SMTP_FROM", smtpFrom);
        try {
            commonSendMail(null, null, p, username, password, toAddresses);
        } catch (Exception e) {
            log.info("send email is fail", e);
        }
    }


    private void commonSendMail(String subject, String content, Properties properties,String userName,String password, String toAddresses) throws EmailException {
        Email email = new HtmlEmail();
        email.setHostName(properties.getProperty("SMTP_HOST"));
        try{
            email.setSmtpPort(Integer.valueOf(properties.getProperty("SMTP_PORT")));
        }catch (NumberFormatException e){
            log.info("sendTextMail: email port info not correct: "+e.getMessage());
        }
        if(properties.get("SMTP_SSL_ENABLE") != null) {
            email.setSSLOnConnect(BooleanUtils.toBoolean(properties.get("SMTP_SSL_ENABLE").toString()));
        }

        // 如果username/password均为null, 那么就不设置authenticator
        if(StringUtils.isNotEmpty(userName) || StringUtils.isNotEmpty(password)) {
            log.info("username is {},password is {}",userName,password);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
        }
        email.setCharset("UTF-8");
        email.setFrom(properties.getProperty("SMTP_FROM"));
        email.setSubject("来自SmartCMP的测试邮件");
        email.setMsg("<div>邮件测试</div>");
        email.addTo(toAddresses);
        email.setSocketTimeout(15000);
        email.setSocketConnectionTimeout(15000);
        email.send();
        log.info("success to send email");
    }

    public static void main(String[] args) {
//        "publisher" -> "MicrosoftWindowsServer"

        String publisher="MicrosoftWindowsServer";
        System.out.println(publisher.contains("windows"));

    }
}
