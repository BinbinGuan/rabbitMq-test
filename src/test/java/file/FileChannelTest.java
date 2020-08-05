package file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: GuanBin
 * @date: Created in 上午9:29 2020/8/5
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("/Users/guanbinbin/linshi/resource.paas.rds.mysql.azure/main.json", "rw");
        FileChannel channel = accessFile.getChannel();
        ByteBuffer bf = ByteBuffer.allocate(1024);
        int read = channel.read(bf);

        System.out.println(new String(bf.array()));

        while (read!=-1){
            System.out.println("Read "+ read);
            bf.flip();
            while (bf.hasRemaining()){
                System.out.println((char) bf.get());
            }
            bf.clear();
            bf.rewind();
            read= channel.read(bf);
        }
        accessFile.close();

//        String sendString="你好,服务器. ";
//        ByteBuffer sendBuffer=ByteBuffer.wrap(sendString.getBytes("UTF-16"));


    }
}
