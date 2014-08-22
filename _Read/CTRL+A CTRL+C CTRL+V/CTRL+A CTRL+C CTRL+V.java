public class Solution {
    public int findMaxM(int n) {
        int[] memo = new int[n + 1];
        return dpFindMaxM(n, memo);
    }

    private int dpFindMaxM(int n, int[] memo) {
        if (n <= 3)
            return n;
        if (memo[n] != 0)
            return memo[n];

        int max = n;
        for (int i = 4; n - i > 1; i++) {
            max = Math.max(max, dpFindMaxM(n - i, memo) * (i - 2));
        }
        memo[n] = max;
        return memo[n];
    }
}