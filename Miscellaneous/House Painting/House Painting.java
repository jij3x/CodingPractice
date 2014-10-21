public class Solution {
    public int[] paintHouses(int[][] costTbl) {
        int houseTTL = costTbl.length;
        int colorTTL = costTbl[0].length;
        int[][] memo = new int[houseTTL][colorTTL];
        for (int i = 0; i < colorTTL; i++)
            memo[0][i] = costTbl[0][i];

        for (int i = 1; i < houseTTL; i++) {
            for (int j = 0; j < colorTTL; j++) {
                int minIdx = findMin(memo[i - 1], j);
                memo[i][j] = memo[i - 1][minIdx] + costTbl[i][j];
            }
        }

        int[] result = new int[houseTTL];
        for (int i = houseTTL - 1, skip = -1; i >= 0; i--) {
            int minIdx = findMin(memo[i], skip);
            result[i] = minIdx;
            skip = minIdx;
        }
        return result;
    }

    private int findMin(int[] A, int skip) {
        int minIdx = skip == 0 ? 1 : 0;
        int minCost = A[minIdx];
        for (int i = 0; i < A.length; i++) {
            if (i != skip) {
                int cost = A[i];
                if (cost < minCost) {
                    minIdx = i;
                    minCost = cost;
                }
            }
        }
        return minIdx;
    }
}
