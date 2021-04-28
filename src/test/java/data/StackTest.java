package data;

public class StackTest {

    public static void main(String[] args) {
        Stack stack = new Stack(10);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        stack.push("f");
        stack.push("g");
        stack.push("h");
        stack.push("i");
        stack.push("j");
        System.out.println(stack.pop());
        System.out.println(stack.peek());

    }
}
