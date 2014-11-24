public class Solution {
    public int climbStairs(int n) {
        int prev = 1;
        for (int i = 2, prevPrev = 1; i <= n; i++) {
            int result = prev + prevPrev;
            prevPrev = prev;
            prev = result;
        }
        return prev;
    }
}

class Solution2 {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return dpClimbStairs(n, memo);
    }

    private int dpClimbStairs(int n, int[] memo) {
        if (n <= 1)
            return 1;

        if (memo[n] == 0)
            memo[n] = dpClimbStairs(n - 1, memo) + dpClimbStairs(n - 2, memo);
        return memo[n];
    }
}
