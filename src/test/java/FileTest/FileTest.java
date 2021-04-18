package FileTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author: GuanBin
 * @date: Created in 下午3:54 2021/4/2
 */
public class FileTest {
    public static void main(String[] args) throws FileNotFoundException {
        String temporaryPath="/Users/guanbinbin/Downloads/WorkSpace/upload/gu01";
        File file = new File(temporaryPath);
        if(!file.exists()){
            boolean mkdir = file.mkdir();
            System.out.println(mkdir);
        }

        String fileName = String.format("%s%s%s", temporaryPath, "aaa", ".txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8);

        System.out.println("asd");
    }
}
