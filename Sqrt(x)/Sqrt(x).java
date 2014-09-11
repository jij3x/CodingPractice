public class Solution {
    public int sqrt(int x) {
        int start = 0, end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if ((long) mid * mid == (long) x)
                return mid;
            else if ((long) mid * mid < (long) x)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return end;
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
