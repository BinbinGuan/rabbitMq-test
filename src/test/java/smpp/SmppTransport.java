package smpp;

import org.smpp.Data;
import org.smpp.Session;
import org.smpp.TCPIPConnection;
import org.smpp.pdu.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: GuanBin
 * @date: Created in 上午10:43 2020/2/7
 */
public class SmppTransport {
    static Map sessionMap=new HashMap<String,String>();
    String result=null;
    public String send(String url, Map<String, String> map) throws Exception {
        int smscPort = Integer.parseInt(map.get("port"));
        String smscHost = map.get("send_url");
        String smscUsername = map.get("username");
        String smscPassword = map.get("password");
        String recipientPhoneNumber = map.get("phone_num");
        String messageText = map.get("text");

        // set values
        try {
            SubmitSM request = new SubmitSM();
            // request.setSourceAddr(createAddress(senderPhoneNumber)); // you can skip this
            request.setDestAddr(createAddress(recipientPhoneNumber));
            request.setShortMessage(messageText);
//            request.setShortMessage(messageText,Data.ENC_UTF8);
            // request.setScheduleDeliveryTime(deliveryTime);           // you can skip this
            request.setReplaceIfPresentFlag((byte) 0);
            request.setEsmClass((byte) 0);
            request.setProtocolId((byte) 0);
            request.setPriorityFlag((byte) 0);
            request.setRegisteredDelivery((byte) 1); // we want delivery reports
            request.setDataCoding((byte) 0);
            request.setSmDefaultMsgId((byte) 0);

            Session session = getSession(smscHost, smscPort, smscUsername, smscPassword);
            SubmitSMResp response = session.submit(request);
            result=new String(response.toString());
        } catch (Exception e) {
            result=StackTraceToString(e);
        }
        return result;
    }

    private Session getSession(String smscHost, int smscPort, String smscUsername, String smscPassword) throws Exception{

        if(sessionMap.containsKey(smscUsername)) {
            return (Session) sessionMap.get(smscUsername);
        }

        BindRequest request = new BindTransmitter();
        request.setSystemId(smscUsername);
        request.setPassword(smscPassword);
        request.setSystemType("smpp");

        // request.setAddressRange(addressRange);
        request.setInterfaceVersion((byte) 0x34); // SMPP protocol version

        TCPIPConnection connection = new TCPIPConnection(smscHost, smscPort);
        // connection.setReceiveTimeout(BIND_TIMEOUT);
        Session session = new Session(connection);
        sessionMap.put(smscUsername, session.toString());

        BindResponse response = session.bind(request);
        return session;
    }

    private Address createAddress(String address) throws WrongLengthOfStringException {
        Address addressInst = new Address();
        addressInst.setTon((byte) 5); // national ton
        addressInst.setNpi((byte) 0); // numeric plan indicator
        addressInst.setAddress(address, Data.SM_ADDR_LEN);
        return addressInst;
    }

    public String StackTraceToString(Exception err) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        err.printStackTrace(pw);
        return sw.toString();
    }



    public String sendSMS(String Port, String Host,String SMPPUserName,String SMPPPassword,String Phone_Number,String Message) throws Exception {
        String response=null;
        sessionMap.put("port",Port);
        sessionMap.put("send_url",Host);
        sessionMap.put("username",SMPPUserName);
        sessionMap.put("password",SMPPPassword);
        sessionMap.put("phone_num",Phone_Number);
        sessionMap.put("text",Message);
        Set set=sessionMap.entrySet();//Converting to Set so that we can traverse
        Iterator itr=set.iterator();
        while(itr.hasNext()){

            Map.Entry entry=(Map.Entry)itr.next();
        }

        SmppTransport test =new SmppTransport();
        try {
            response=test.send("10.50.**.**", sessionMap);
            System.out.println(response);
        } catch (Exception e) {

            response=StackTraceToString(e);
        }
        return response;

    }

    public static void main(String[] args) throws Exception
    {
        SmppTransport sm=new SmppTransport();
//        String test=sm.sendSMS("80*6", "10.50.**.**", "f***obi", "x***fQ", "+9187965*****", "Testing1");
        String test=sm.sendSMS("8085", "127.0.0.1", "f***obi", "x***fQ", "+9187965*****", "Testing1");
        System.out.println("Data: "+test);

    }
}
