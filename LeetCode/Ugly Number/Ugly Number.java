public class Solution {
    public boolean isUgly(int num) {
        for (int f = 2; f <= 5 && num > 0; f++) {
            while (num % f == 0)
                num /= f;
        }
        return num == 1;
    }
}