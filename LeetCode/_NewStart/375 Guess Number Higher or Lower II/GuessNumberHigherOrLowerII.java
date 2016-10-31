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

