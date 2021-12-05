import org.junit.Test;

import java.util.function.Consumer;

// *** The point of lambda expression relating to functional interface is the lambda EXPRESSION
// is defining the single method accept() method for Consumer<T>, inside the functional
// interface

public class ConsumerTest {

    @FunctionalInterface
    interface TestInterface {
        public void someMethod();
    }

    // Using custom functional interface TestInterface
    @Test
    public void consumerTestMethod1() {
        // lambda expression defines what TestInterface functional interface's someMethod() does
        TestInterface tI;
        tI = () -> {
          System.out.println("hi");
        };

        tI.someMethod();
    }

    // Using built-in functional interface Consumer<T>
    @Test
    public void consumerTestMethod2() {
        // lambda expression defines what Consumer<T> functional interface's accept() does
        Consumer<String> consumer;
        consumer = a -> {
            System.out.println(a);
        };
        consumer.accept("this will be printed per lambda expression defined");
    }

}
