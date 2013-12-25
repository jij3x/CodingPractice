public class Solution {
	public int largestRectangleArea(int[] height) {
		if (height.length == 0)
			return 0;
		if (height.length == 1)
			return height[0];

		int maxArea = height[0];
		Stack<Integer> heightStack = new Stack<Integer>();
		Stack<Integer> indexStack = new Stack<Integer>();
		indexStack.push(0);
		heightStack.push(height[0]);

		for (int i = 1; i < height.length; i++) {
			if (height[i] > heightStack.peek()) {
				heightStack.push(height[i]);
				indexStack.push(i);
			} else if (height[i] < heightStack.peek()) {
				int lastPos = 0;
				while (!heightStack.isEmpty() && height[i] < heightStack.peek()) {
					lastPos = indexStack.peek();
					maxArea = Math.max(maxArea, heightStack.pop() * (i - indexStack.pop()));
				}
				heightStack.push(height[i]);
				indexStack.push(lastPos);
			}
		}

		while (!heightStack.isEmpty()) {
			maxArea = Math.max(maxArea, heightStack.pop() * (height.length - indexStack.pop()));
		}

		return maxArea;
	}
}