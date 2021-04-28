package anonymous;

/**
 * @author: GuanBin
 * @date: Created in 上午10:27 2021/1/27
 */
public class AnonymousTest {

    public static void main(String[] args) {
        //匿名内部类
        Person p = new Person() {
            @Override
            public void eat() {
                System.out.println("吃苹果");
            }
        };
        p.eat();

        Animal animal = new Animal() {
            @Override
            public void play() {
                System.out.println("玩玩具");
            }
        };

        animal.play();
    }


}
