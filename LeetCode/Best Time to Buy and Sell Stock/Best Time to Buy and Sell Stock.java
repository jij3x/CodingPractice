public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;

        int maxDiff = 0;
        for (int i = 1, lowest = prices[0]; i < prices.length; i++) {
            lowest = Math.min(lowest, prices[i]);
            maxDiff = Math.max(maxDiff, prices[i] - lowest);
        }
        return maxDiff;
    }
}
