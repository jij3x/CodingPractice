public class Solution {
	public int maxArea(int[] height) {
		int head = 0;
		int end = height.length - 1;
		int max = 0;
		while (head < end) {
			max = Math.max(max, Math.min(height[head], height[end]) * (end - head + 1));
			if (height[head] >= height[end])
				end--;
			else
				head++;
		}

		return max;
	}
}
