package com.example.xxl;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: GuanBin
 * @date: Created in 上午10:54 2020/11/12
 */
@Component
public class SamplXxlJob {

    private static Logger logger = LoggerFactory.getLogger(SamplXxlJob.class);


    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("testJobHandler")
    public ReturnT<String> testJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, taskJobHandler start---------------");
        try {
//            taskJob.execute(param);
            return ReturnT.SUCCESS;
        } catch (Exception exception) {
            XxlJobLogger.log("taskJobHandler发生异常:{}", exception.getMessage(), exception);
            if (exception instanceof InterruptedException) {
                throw exception;
            }
            return new ReturnT<>(ReturnT.FAIL_CODE, exception.getMessage());
        } finally {
            XxlJobLogger.log("XXL-JOB, taskJobHandler end---------------");
        }
    }
}
