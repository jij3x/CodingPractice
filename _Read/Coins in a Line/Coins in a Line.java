public class Solution {
    private int[] sumTbl;

    public int maxMoney(int[] A) {
        sumTbl = new int[A.length + 1];
        for (int i = 1; i <= A.length; i++)
            sumTbl[i] = sumTbl[i - 1] + A[i];

        int[][] memo = new int[A.length][A.length];
        return dpMaxMoney1(A, 0, A.length - 1, memo);
    }

    private int dpMaxMoney1(int[] A, int i, int j, int[][] memo) {
        if (i > j)
            return 0;
        if (memo[i][j] != 0)
            return memo[i][j];

        memo[i][j] = sumTbl[j + 1] - sumTbl[i];
        memo[i][j] -= Math.min(dpMaxMoney1(A, i + 1, j, memo), dpMaxMoney1(A, i, j + 1, memo));
        return memo[i][j];
    }

    private int dpMaxMoney2(int[] A, int i, int j) {
        int[][] memo = new int[A.length][A.length];
        for (int m = A.length - 1; m >= 0; m--) {
            for (int n = m; n < A.length; n++) {
                int option1 = m + 2 < A.length ? memo[m + 2][n] : 0;
                int option2 = n - 2 >= 0 ? memo[m][n - 2] : 0;
                int option3 = m + 1 < A.length && n - 1 >= 0 ? memo[m + 1][n - 1] : 0;
                memo[m][n] = Math.max(A[m] + Math.min(option1, option3), A[n] + Math.min(option2, option3));
            }
        }
        return memo[0][A.length - 1];
    }
}