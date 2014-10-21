public class Solution {
    public double sqrt(double x) {
        double start = 0, end = x;
        while (end - start > 1e-9) {
            double mid = (start + end) / 2;
            if (mid * mid > x)
                end = mid;
            else
                start = mid;
        }
        return end;
    }
}