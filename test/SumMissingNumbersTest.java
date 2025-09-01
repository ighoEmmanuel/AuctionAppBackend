import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumMissingNumbersTest {

    @Test
    public void testSumMissingNumbers() {
        assertEquals(10,SumMissingNumbers.sum_missing_number("5"));
    }


    @Test
    public void testSumMissingNumbers2() {
        assertEquals(11,SumMissingNumbers.sum_missing_number("2,3,4,7"));
    }

    @Test
    public void testSumMissingNumbers3() {
        assertEquals(74,SumMissingNumbers.sum_missing_number("16,7,8,10"));
    }

    @Test
    public void testSumMissingNumbers4() {
        assertEquals(5, SumMissingNumbers.sum_missing_number("1,2,3,4,6,7"));
    }

    @Test
    public void testSumMissingNumbers5() {
        assertEquals(10, SumMissingNumbers.sum_missing_number("0,5"));
    }
}