public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2)
            return 0;

        int[] maxOnLeft = new int[prices.length];
        for (int i = 1, lowest = prices[0]; i < prices.length; i++) {
            lowest = Math.min(lowest, prices[i]);
            maxOnLeft[i] = Math.max(maxOnLeft[i - 1], prices[i] - lowest);
        }

        int[] maxOnRight = new int[prices.length];
        for (int i = prices.length - 2, highest = prices[prices.length - 1]; i >= 0; i--) {
            highest = Math.max(highest, prices[i]);
            maxOnRight[i] = Math.max(maxOnRight[i + 1], highest - prices[i]);
        }

        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, maxOnLeft[i - 1] + maxOnRight[i]);
        }

        return Math.max(max, Math.max(maxOnLeft[prices.length - 1], maxOnRight[0]));
    }
}
