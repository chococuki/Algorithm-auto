import java.util.*;

class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        while (n > 0) {
            int remainder = n % 3;
            n = n / 3;

            if (remainder == 0) {
                remainder = 4;
                n--;
            }

            answer.insert(0, remainder);
        }

        return answer.toString();
    }
}