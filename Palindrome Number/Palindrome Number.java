public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;

        int divisor = 1;
        while (x / divisor > 9)
            divisor *= 10;

        while (divisor > 1) {
            if (x / divisor != x % 10)
                return false;

            x = x % divisor / 10;
            divisor /= 100;
        }
        return true;
    }
}
