package basic.error;

public class Stack {
    public static void main(String[] args) {
        new Stack().test();
    }

    public void test() {
        System.out.println("递归调用死循环");
        test();
    }
}