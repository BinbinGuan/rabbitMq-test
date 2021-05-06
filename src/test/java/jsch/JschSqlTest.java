package jsch;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: GuanBin
 * @date: Created in 下午3:58 2021/4/20
 */
public class JschSqlTest {

    public static void main(String[] args) throws SQLException, JSchException {
        test();
    }


    private static void test() throws JSchException, SQLException {
        Session session = null;
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session = jsch.getSession("root", "192.168.86.60", 22);
            session.setPassword("Passw0rd");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            //内部 mysql 信息
            String mysqlHost = "192.168.86.60";
            int mysqlPort = 3306;
            String db = "mysql";
            String user = "root";
            String password = "c10udch3f";

            int bindPort = session.setPortForwardingL("192.168.86.60", 53307, mysqlHost, mysqlPort);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:" + bindPort + "/" + db + "?useSSL=false", user, password);
            if (connection.isValid(3)) {
                System.out.println("数据库连接成功");
            } else {
                System.out.println("数据库连接失败");
            }
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
