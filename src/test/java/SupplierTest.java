import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SupplierTest {

    // Pass Supplier function interface as an argument to printAccess(Supplier<Boolean> supplier) method
    @Test
    public void supplierTest1() {
        String password = "valid";
        printAccess(() -> {                         // anonymous interface implementation. not an anonymous function
            if (password.equals("valid"))
                return true;
            else
                return false;
        });
    }

    private void printAccess(Supplier<Boolean> supplier) {
        if(supplier.get() == true)                                  // get just executes the body of anonymous interface implementation defined (if-else statement) above? YES
            System.out.println("Correct password - unlocked");
        else
            System.out.println("Incorrect password - locked");
    }

    // Override Supplier get() method
    @Test
    public void supplierOverrideTest2() {
        Supplier<String> supplier = new Supplier() {
            @Override
            public String get() {
                return "supplier get() override";
            }
        };
        System.out.println(supplier.get());
    }
}
