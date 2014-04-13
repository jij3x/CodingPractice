public class Solution {
    public int sqrt(int x) {
        long start = 1, end = x;
        while (start <= end) {
            long mid = (start + end) / 2;
            if (mid * mid == x)
                return (int) mid;
            else if (mid * mid > x)
                end = mid - 1;
            else
                start = mid + 1;
        }

        return (int) end;
    }
}

class Solution2 {
    public int sqrt(int x) {
        double epsilon = 1e-10, r = x;
        while (Math.abs(r - x / r) > epsilon * r)
            r = (x / r + r) / 2.0;

        return (int) r;
    }
}
