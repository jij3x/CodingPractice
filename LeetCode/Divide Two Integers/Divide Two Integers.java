public class Solution {
    public int divide(int dividend, int divisor) {
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor), bb = b;

        long c = 0, f = 1;
        while (a >= bb) {
            if (b <= a) {
                a -= b;
                c += f;
                b <<= 1;
                f <<= 1;
            } else {
                b >>= 1;
                f >>= 1;
            }
        }
        c = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? c : 0 - c;
        return (int) Math.min(c, Integer.MAX_VALUE);
    }
}
