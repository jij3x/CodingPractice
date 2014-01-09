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
				int lastPosition = 0;
				while (!heightStack.isEmpty() && height[i] < heightStack.peek()) {
					lastPosition = indexStack.peek();
					maxArea = Math.max(maxArea, heightStack.pop() * (i - indexStack.pop()));
				}
				heightStack.push(height[i]);
				indexStack.push(lastPosition);
			}
		}

		while (!heightStack.isEmpty()) {
			maxArea = Math.max(maxArea, heightStack.pop() * (height.length - indexStack.pop()));
		}

		return maxArea;
	}
}

/*
 * Very tricky & complicated. Not suitable for special event.
 */
class Solution2 {
	public int largestRectangleArea(int[] height) {
		int[] h = new int[height.length + 1];
		System.arraycopy(height, 0, h, 0, height.length);
		h[h.length - 1] = 0;

		Stack<Integer> workingStack = new Stack<Integer>();
		int i = 0;
		int maxArea = 0;
		while (i < h.length) {
			if (workingStack.isEmpty() || h[workingStack.peek()] <= h[i]) {
				workingStack.push(i++);
			} else {
				int t = workingStack.pop();
				maxArea = Math.max(maxArea, h[t] * (workingStack.isEmpty() ? i : i - workingStack.peek() - 1));
			}
		}
		return maxArea;
	}
}
