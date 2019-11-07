package com.example.guava;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.google.common.base.Predicates;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author: GuanBin
 * @date: Created in 下午4:30 2019/11/3
 */
public class RetryTest {

    public static void main(String[] args) {
        //callable是定义具体的业务操作，并返回该操作结果的返回值
        Callable<Boolean> callable = new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return true; // do something useful here
            }
        };

        //定义retryer，指定轮询条件，及结束条件
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.<Boolean>isNull())
                .retryIfExceptionOfType(IOException.class)
                .retryIfRuntimeException()
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        try {
            retryer.call(callable);
        } catch (RetryException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
