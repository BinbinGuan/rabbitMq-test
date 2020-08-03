package sql;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 上午11:06 2020/3/26
 */
public class SqlTest {

    public static void main(String[] args) {
        try {
//            List<String> sqlList = loadSql("src/test/resources/sql/test.sql");
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            String sql="classpath:/sql/test.sql";
            File file = resourceLoader.getResource(sql).getFile();
            List<String> sqlList=   loadSql(file);
            System.out.println("size:" + sqlList.size());
            for (String sq : sqlList) {
                System.out.println(sq);
            }

        } catch (Exception e) {

        }
    }

    public static List<String> loadSql(File  sqlFile) throws Exception {
        List<String> sqlList = new ArrayList<String>();
        try {
            InputStream sqlFileIn = new FileInputStream(sqlFile);
            StringBuffer sqlSb = new StringBuffer();
            byte[] buff = new byte[1024];
            int byteRead = 0;
            while ((byteRead = sqlFileIn.read(buff)) != -1) {
                sqlSb.append(new String(buff, 0, byteRead));
            }

            // Windows 下换行是 \r\n, Linux 下是 \n
            String[] sqlArr = sqlSb.toString()
                    .split("(;\\s*\\r\\n)|(;\\s*\\n)");
            for (int i = 0; i < sqlArr.length; i++) {
                String sql = sqlArr[i].replaceAll("--.*", "").trim();
                if (!sql.equals("")) {
                    sqlList.add(sql);
                }
            }
            return sqlList;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
