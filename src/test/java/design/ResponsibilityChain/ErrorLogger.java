package design.ResponsibilityChain;

/**
 * @author: GuanBin
 * @date: Created in 下午8:38 2020/8/5
 */
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
