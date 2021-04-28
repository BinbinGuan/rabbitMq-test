package com.example.script;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.*;

/**
 * @author: GuanBin
 * @date: Created in 下午4:16 2020/11/27
 */
@RestController
@RequestMapping("/script")
public class ScriptController {
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/execute", method = RequestMethod.GET)//      Reader reader = new BufferedReader(new FileReader("E:\\sampleScript.sql"));
    public void executeScript() throws IOException {
        String test = test();
        ScriptRunner runner;
        try {
            runner = new ScriptRunner(dataSource.getConnection());
            runner.setAutoCommit(true);
            runner.setStopOnError(true);
//            Reader reader = new BufferedReader(new FileReader("/Users/guanbinbin/Downloads/WorkSpace/sql/delete-tenant-data.sql"));

//            runner.runScript(Resources.getResourceAsReader("scripts/yourschema.sql"));
            runner.runScript(Resources.getResourceAsReader("sql/delete-tenant-data.sql"));
            runner.runScript(new StringReader(test));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String test() throws IOException {
        String sql="";
        //原有的内容
        String srcStr = "\\$\\{tenantId}";
        //要替换的内容
        String replaceStr = "test123";
        // 读
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        File file = resourceLoader.getResource("classpath:/sql/delete-tenant-data.sql").getFile();
        FileReader in = new FileReader(file);
        BufferedReader bufIn = new BufferedReader(in);
        // 内存流, 作为临时流
        CharArrayWriter  tempStream = new CharArrayWriter();
        // 替换
        String line = null;
        while ( (line = bufIn.readLine()) != null) {
            // 替换每行中, 符合条件的字符串
            line = line.replaceAll(srcStr, replaceStr);
            // 将该行写入内存
            tempStream.write(line);
            // 添加换行符
            tempStream.append(System.getProperty("line.separator"));
        }
        // 关闭 输入流
        bufIn.close();
        // 将内存中的流 写入 文件
        sql= tempStream.toString();
        tempStream.close();
        return sql;
    }
}
