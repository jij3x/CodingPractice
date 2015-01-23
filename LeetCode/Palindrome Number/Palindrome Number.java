public class Solution {
    public boolean isPalindrome(int x) {
        int y = x, z = 0;
        while (x != 0) {
            z = z * 10 + x % 10;
            x /= 10;
        }
        return y >= 0 && y == z;
    }
}

class Solution2 {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;

        int divisor = 1;
        while (x / divisor > 9)
            divisor *= 10;

        while (divisor >= 10) {
            if (x / divisor != x % 10)
                return false;

            x = (x % divisor) / 10;
            divisor /= 100;
        }
        return true;
    }
}
