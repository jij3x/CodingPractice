public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;

        int maxDiff = 0;
        for (int lowestSoFar = prices[0], i = 1; i < prices.length; i++) {
            maxDiff = Math.max(maxDiff, prices[i] - lowestSoFar);
            lowestSoFar = Math.min(lowestSoFar, prices[i]);
        }
        return maxDiff;
    }
}
