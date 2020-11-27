package com.example.script;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

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
    public void executeScript(){
        ScriptRunner runner;
        try {
            runner = new ScriptRunner(dataSource.getConnection());
            runner.setAutoCommit(true);
            runner.setStopOnError(true);
//            Reader reader = new BufferedReader(new FileReader("/Users/guanbinbin/Downloads/WorkSpace/sql/delete-tenant-data.sql"));

//            runner.runScript(Resources.getResourceAsReader("scripts/yourschema.sql"));
            runner.runScript(Resources.getResourceAsReader("sql/delete-tenant-data.sql"));
//            runner.runScript(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
