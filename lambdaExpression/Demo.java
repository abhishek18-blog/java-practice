/* 
interface A {
    public void show();
}

// code before lambdaexpression
public class Demo {
public static void main(String[] args) {

A obj = new A() {
public void show() {
System.out.println("Im show bro");

}
};
obj.show();

}
}

// code after lambdaexpression

public class Demo {
    public static void main(String[] args) {
        A obj = () -> System.out.println("show");
        obj.show();
    }
}
*/
@FunctionalInterface
// lets say we have return type
interface sum {
    void add(int a, int b);
}

public class Demo {
    public static void main(String[] args) {

        sum s = (a, b) -> System.out.println(a + b);
        s.add(5, 5);

    }
}