public class Solution {
    public int findMax(int[] A, int k) {
        return partition(A, 0, A.length - 1, k);
    }

    private int sum(int[] A, int start, int end) {
        int result = 0;
        for (int i = start; i <= end; i++)
            result += A[i];
        return result;
    }

    private int partition(int[] A, int start, int end, int k) {
        if (k == 1)
            return sum(A, start, end);

        int best = Integer.MAX_VALUE;
        for (int i = start; i <= end - k - 1; i++)
            best = Math.min(best, Math.max(sum(A, start, i), partition(A, i + 1, end, k - 1)));
        return best;
    }

    public int dpFindMax(int[] A, int k) {
        long[] sum = new long[A.length + 1];
        for (int i = 1; i <= A.length; i++)
            sum[i] = sum[i - 1] + A[i - 1];

        int[][] memo = new int[A.length][k];
        memo[A.length - 1][0] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--)
            memo[i][0] = A[i] + memo[i + 1][0];

        for (int i = 1; i < k; i++) {
            for (int j = A.length - 2; j >= 0; j--) {
                memo[j][i] = Integer.MAX_VALUE;
                for (int p = j; p <= A.length - 1 - i; p++) {
                    memo[j][i] = Math.min(memo[j][i], Math.max((int) (sum[p + 1] - sum[j]), memo[p + 1][i - 1]));
                }
            }
        }
        return memo[0][k - 1];
    }
}