import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.min;

public class SumMissingNumbers {
        public static int sum_missing_number(String input) {
            String[] parts = input.split(",");
            List<Integer> givenNumbers = new ArrayList<>();
            int total = 0;
            for (String part : parts) {
                int num = Integer.parseInt(part.trim());
                givenNumbers.add(num);
                if (num > total) total = num;
            }
            int sum = 0;
            if (givenNumbers.size() == 1) {
                for (int i = 1; i <= total; i++) {
                    if (!givenNumbers.contains(i)) {
                        sum += i;
                    }
                }
            }else{
                int num  = Collections.min(givenNumbers);
                for (int i = num ; i <= total; i++) {
                    if (!givenNumbers.contains(i)) {
                        sum += i;
                    }
                }
            }
            return sum;
        }


    }





