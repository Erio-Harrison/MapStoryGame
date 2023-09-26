package Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;




public class TestCases {

    @Test
    public void addition_shouldAddTwoNumbersCorrectly() {
        int a = 5;
        int b = 3;
        int expected = 8;
        int actual = a + b;
        assertEquals(expected, actual, "5 + 3 should equal 8");
    }
}
