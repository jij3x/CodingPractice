public class Solution {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0)
            return 0;
        if (n == 1)
            return k;

        int prevPrev = k, prev = k * k;
        for (int i = 3; i <= n; i++) {
            int r = (k - 1) * (prev + prevPrev);
            prevPrev = prev;
            prev = r;
        }
        return prev; 
    }
}