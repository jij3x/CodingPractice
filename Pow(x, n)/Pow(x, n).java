public class Solution {
    public double pow(double x, int n) {
        if (n == 0)
            return 1;

        double half = pow(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        else if (n > 0)
            return half * half * x;
        else
            return half * half / x;
    }
}

class Solution2 {
    public double pow(double x, int n) {
        int nn = Math.abs(n);
        double result = 1.0;
        while (nn > 0) {
            if ((nn & 1) == 1)
                result *= x;
            x *= x;
            nn >>= 1;
        }

        return n > 0 ? result : 1.0 / result;
    }
}
