public class Solution {
    public int divide(int dividend, int divisor) {
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor), bbk = b;

        int result = 0, factor = 1;
        while (a >= bbk) {
            if (a < b) {
                b >>= 1;
                factor >>= 1;
            } else if (a >= b << 1) {
                b <<= 1;
                factor <<= 1;
            } else {
                result += factor;
                a -= b;
            }
        }

        return (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? result : -result;
    }
}
