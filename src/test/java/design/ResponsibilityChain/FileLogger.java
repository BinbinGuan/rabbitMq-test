package design.ResponsibilityChain;

/**
 * @author: GuanBin
 * @date: Created in 下午8:38 2020/8/5
 */
public class FileLogger extends AbstractLogger {
    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
