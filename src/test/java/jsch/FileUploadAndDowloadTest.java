package jsch;

import com.jcraft.jsch.*;
import lombok.Data;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: GuanBin
 * @date: Created in 下午1:58 2021/4/21
 */
public class FileUploadAndDowloadTest {

    public static void getConnect(SFTP s) throws Exception {
        /** 密钥的密码  */
//      String privateKey ="key";
//        /** 密钥文件路径  */
//      String passphrase ="path";
        /** 主机 */
        String host = "192.168.86.60";
        /** 端口 */
        int port = 22;
        /** 用户名 */
        String username = "root";
        /** 密码 */
        String password = "Passw0rd";
        Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;// sftp操作类
        JSch jsch = new JSch();

        //设置密钥和密码
        //支持密钥的方式登陆，只需在jsch.getSession之前设置一下密钥的相关信息
//      if (privateKey != null && !"".equals(privateKey)) {
//             if (passphrase != null && "".equals(passphrase)) {
//              //设置带口令的密钥
//                 jsch.addIdentity(privateKey, passphrase);
//             } else {
//              //设置不带口令的密钥
//                 jsch.addIdentity(privateKey);
//             }
//      }
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no"); // 不验证 HostKey
        session.setConfig(config);
        try {
            session.connect();
        } catch (Exception e) {
            if (session.isConnected())
                session.disconnect();
        }
        channel = session.openChannel("sftp");
        Channel shell = session.openChannel("shell");
        try {
            channel.connect();
        } catch (Exception e) {
            if (channel.isConnected())
                channel.disconnect();
        }
        sftp = (ChannelSftp) channel;
        s.setChannel(channel);
        s.setSession(session);
        s.setSftp(sftp);
    }

    /*断开连接*/
    public static void disConn(Session session, Channel channel, ChannelSftp sftp) throws Exception {
        if (null != sftp) {
            sftp.disconnect();
            sftp.exit();
            sftp = null;
        }
        if (null != channel) {
            channel.disconnect();
            channel = null;
        }
        if (null != session) {
            session.disconnect();
            session = null;
        }
    }


    public static void main(String[] args) throws Exception {

//        Path test = Paths.get("/root", "test");
//        System.out.println(test.getFileName());

        listDir();
    }
    public static void listDir() throws Exception {
        SFTP s = new SFTP();
        getConnect(s);//建立连接
        Session session = s.getSession();
        Channel channel = s.getChannel();
        ChannelSftp sftp = s.getSftp();// sftp操作类

//        sftp.put( new ByteArrayInputStream( "".getBytes() ), "/root/0421.txt");
        sftp.getHome();
        sftp.stat("/root");

        Vector filelist = sftp.ls("/root");
        for(int i=0; i<filelist.size();i++){

//            ChannelSftp.LsEntry lsEntry = sftp.LsEntry();

            ChannelSftp.LsEntry o = (ChannelSftp.LsEntry) filelist.get(i);
            o.getAttrs();o.getAttrs().isDir();
            o.getAttrs().getPermissions();
            System.out.println(filelist.get(i).toString());
        }
        disConn(s.getSession(),s.getChannel(),s.getSftp());

    }

    /**
     * 上传文件
     *
     * @param directory  上传的目录-相对于SFPT设置的用户访问目录，
     *                   为空则在SFTP设置的根目录进行创建文件（除设置了服务器全磁盘访问）
     * @param uploadFile 要上传的文件全路径
     */
    public static void upload(String directory, String uploadFile) throws Exception {
        SFTP s = new SFTP();
        getConnect(s);//建立连接
        Session session = s.getSession();
        Channel channel = s.getChannel();
        ChannelSftp sftp = s.getSftp();// sftp操作类
        try {
            try {
                sftp.cd(directory); //进入目录
            } catch (SftpException sException) {
                if (sftp.SSH_FX_NO_SUCH_FILE == sException.id) { //指定上传路径不存在
                    sftp.mkdir(directory);//创建目录
                    sftp.cd(directory);  //进入目录
                }
            }


            File file = new File(uploadFile);
            InputStream in = new FileInputStream(file);
            sftp.put(in, file.getName());
            in.close();
        } catch (Exception e) {

        } finally {
            disConn(session, channel, sftp);
        }
    }

    public static void dowload(String directory, String uploadFile) throws Exception {
        SFTP s = new SFTP();
        getConnect(s);//建立连接
        Session session = s.getSession();
        Channel channel = s.getChannel();
        ChannelSftp sftp = s.getSftp();// sftp操作类
        try {
            try {
                sftp.cd(directory); //进入目录
            } catch (SftpException sException) {
                if (sftp.SSH_FX_NO_SUCH_FILE == sException.id) { //指定上传路径不存在
                    sftp.mkdir(directory);//创建目录
                    sftp.cd(directory);  //进入目录
                }
            }
            OutputStream out = new FileOutputStream("d:\\4.txt");
//            channel.get("/root/file/1.txt", out);

//            File file = new File(uploadFile);
//            InputStream in = new FileInputStream(file);
//            sftp.put(in, file.getName());
//            in.close();
        } catch (Exception e) {

        } finally {
            disConn(session, channel, sftp);
        }
    }


    @Data
   static class SFTP {
        private Channel channel;
        private Session session;
        private ChannelSftp sftp;
    }

}
