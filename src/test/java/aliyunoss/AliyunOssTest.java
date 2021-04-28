package aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.PutObjectResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 下午6:01 2021/4/1
 */
public class AliyunOssTest {
    public static void main(String[] args) throws FileNotFoundException {
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = "";
        String accessKeySecret = "";
        String bucketName="";

        uploadFIle(endpoint, accessKeyId, accessKeySecret,bucketName);
//        deleteFIle(endpoint, accessKeyId, accessKeySecret,bucketName);
    }

    private static void uploadFIle(String endpoint, String accessKeyId, String accessKeySecret,String bucketName) throws FileNotFoundException {
        File file = new File("/Users/guanbinbin/Downloads/WorkSpace/upload/tmp/cb4811c4-5764-45a0-9937-bdda1402d11f_172.16.3.16_1614760947230.cast");
        InputStream inputStream = new FileInputStream(file);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, "guanbin/guanbin-1.cast", inputStream);
        ossClient.shutdown();
    }

    private static void deleteFIle(String endpoint, String accessKeyId, String accessKeySecret,String bucketName) throws FileNotFoundException {


        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        List<String> keys = new ArrayList<String>();
        keys.add("guanbin/guanbin-1.cast");
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        ossClient.shutdown();

    }
}
