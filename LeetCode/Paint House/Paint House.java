public class Solution {
    public int minCost(int[][] costs) {
        int[][] memo = new int[costs.length + 1][3];
        for (int i = costs.length - 1; i >= 0; i--) {
            memo[i][0] = costs[i][0] + Math.min(memo[i + 1][1], memo[i + 1][2]);
            memo[i][1] = costs[i][1] + Math.min(memo[i + 1][0], memo[i + 1][2]);
            memo[i][2] = costs[i][2] + Math.min(memo[i + 1][0], memo[i + 1][1]);
        }
        return Math.min(memo[0][0], Math.min(memo[0][1], memo[0][2]));
    }
}