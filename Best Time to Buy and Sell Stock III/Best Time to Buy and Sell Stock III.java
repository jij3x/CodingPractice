public class Solution {
	public int maxProfit(int[] prices) {
		if (prices.length < 2)
			return 0;

		int[] leftMax = new int[prices.length];
		for (int i = 1, lowest = prices[0]; i < prices.length; i++) {
			lowest = Math.min(lowest, prices[i]);
			leftMax[i] = Math.max(leftMax[i - 1], prices[i] - lowest);
		}

		int[] rightMax = new int[prices.length];
		for (int i = prices.length - 2, highest = prices[prices.length - 1]; i >= 0; i--) {
			highest = Math.max(highest, prices[i]);
			rightMax[i] = Math.max(rightMax[i + 1], highest - prices[i]);
		}

		int maxProfit = 0;
		for (int i = 1; i < prices.length - 2; i++) {
			maxProfit = Math.max(maxProfit, leftMax[i] + rightMax[i + 1]);
		}

		return Math.max(maxProfit, leftMax[leftMax.length - 1]);
	}
}