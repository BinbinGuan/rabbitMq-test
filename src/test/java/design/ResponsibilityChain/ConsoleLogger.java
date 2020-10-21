package design.ResponsibilityChain;

/**
 * @author: GuanBin
 * @date: Created in 下午8:34 2020/8/5
 */
public class ConsoleLogger extends AbstractLogger{

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
