interface A {
    public void show();
}

// code before lambdaexpression
// public class Demo {
// public static void main(String[] args) {

// A obj = new A() {
// public void show() {
// System.out.println("Im show bro");

// }
// };
// obj.show();

// }
// }

// code after lambdaexpression

public class Demo {
    public static void main(String[] args) {
        A obj = () -> System.out.println("show");
        obj.show();
    }
}