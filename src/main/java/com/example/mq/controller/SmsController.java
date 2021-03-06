package com.example.mq.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.smpp.Data;
import org.smpp.Session;
import org.smpp.TCPIPConnection;
import org.smpp.pdu.*;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * @author: GuanBin
 * @date: Created in 下午5:37 2020/2/17
 */
@RestController
@RequestMapping(value = "/api/sms")
@Slf4j
public class SmsController {


    @RequestMapping(method = RequestMethod.GET, value = "/test")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "测试apo", notes = "")
    public void testMail(@RequestParam(name = "host", required = false) String host,
                         @RequestParam(name = "port", required = false) String port,
                         @RequestParam(name = "userName", required = false) String userName,
                         @RequestParam(name = "password", required = false) String password,
                         @RequestParam(name = "phonenumber", required = false) String phonenumber,
                         @RequestParam(name = "sourceAddress", required = false) String sourceAddress,
                         @RequestParam(name = "verifyCode", required = false) String verifyCode
    ) {
        String pass = StringUtils.trim(password);
        log.info("start to send sms notification, reciever,host {},port {}, userName {} password {} destinations is {} verifyCode {}", host, port, userName, pass, phonenumber, verifyCode);
        try {
            TCPIPConnection connection = new TCPIPConnection(host, Integer.parseInt(port));
            Session session = new Session(connection);
            BindRequest request = new BindTransmitter();
            request.setSystemId(userName);
            request.setPassword(pass);
            //SMPP protocol version
            request.setInterfaceVersion((byte) 0x34);
            request.setSystemType("SMPP");
            BindResponse bind = session.bind(request);
            log.info("bind response debugString {},response command status {}",bind.debugString(),bind.getCommandStatus());
            String content = "[Registration]" + verifyCode + " is your verification code. Valid in 15 minutes. Please do not share this code with anyone else.";
            SubmitSM submitSM = constructRequest(phonenumber,sourceAddress, content);
            //bund faild 会导致TCPIPConnection关闭从而导致outputStream关闭从而导致no
            SubmitSMResp response = session.submit(submitSM);
            log.info("send message result {},command status is {}",response.debugString(),response.getCommandStatus());
        } catch (Exception e) {
            log.error("invoke sms session exception", e);
        }

    }

    private SubmitSM constructRequest(String phoneNumber,String sourceAddress, String content) throws WrongLengthOfStringException, UnsupportedEncodingException {
        String recipientPhoneNumber = phoneNumber;

        SubmitSM request = new SubmitSM();
        request.setSourceAddr(createsSourceAddress(sourceAddress));
        request.setDestAddr(createsDestinationAddress(recipientPhoneNumber));
        request.setShortMessage(content,Data.ENC_UTF8);
        request.setReplaceIfPresentFlag((byte) 0);
        request.setEsmClass((byte) 0);
        request.setProtocolId((byte) 0);
        request.setPriorityFlag((byte) 0);
        request.setRegisteredDelivery((byte) 1);// we want delivery reports
        request.setDataCoding((byte) 0);
        request.setSmDefaultMsgId((byte) 0);
        return request;
    }

    private Address createsSourceAddress(String address) throws WrongLengthOfStringException {
        Address addressInst = new Address();
        // national ton
        addressInst.setTon((byte) 5);
        // numeric plan indicator
        addressInst.setNpi((byte) 0);
        addressInst.setAddress(address, Data.SM_ADDR_LEN);
        return addressInst;
    }

    private Address createsDestinationAddress(String address) throws WrongLengthOfStringException {
        if(address.startsWith("+")) {
            address = address.substring(1);
        }
        Address addressInst = new Address();
        // national ton
        addressInst.setTon((byte) 1);
        // numeric plan indicator
        addressInst.setNpi((byte) 1);
        addressInst.setAddress(address, Data.SM_ADDR_LEN);
        return addressInst;
    }

}
