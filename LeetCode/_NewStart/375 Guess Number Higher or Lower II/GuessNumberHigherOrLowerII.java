public class Solution {
  public int getMoneyAmount(int n) {
    int[][] memo = new int[n + 1][n + 1];
    return dp(1, n, memo);
  }

  private int dp(int left, int right, int[][] memo) {
    if (left >= right) {
      return 0;
    }

    if (memo[left][right] == 0) {
      memo[left][right] = Integer.MAX_VALUE;
      for (int i = left; i <= right; i++) {
        int max = Math.max(dp(left, i - 1, memo), dp(i + 1, right, memo)) + i;
        memo[left][right] = Math.min(memo[left][right], max);
      }
    }
    return memo[left][right];
  }
}

public class Solution2 {
  public int getMoneyAmount(int n) {
    if (n == 1) {
      return 0;
    }

    int[][] dp = new int[n + 1][n + 1];
    for (int right = 1; right <= n; right++) {
      for (int left = right; left >= 1; left--) {
        dp[left][right] = Integer.MAX_VALUE;
        for (int i = right; i >= left; i--) {
          dp[left][right] = Math.min(dp[left][right],
              i + Math.max(left >= i - 1 ? 0 : dp[left][i - 1], i + 1 >= right ? 0 : dp[i + 1][right]));
        }
      }
    }
    return dp[1][n];
  }
}