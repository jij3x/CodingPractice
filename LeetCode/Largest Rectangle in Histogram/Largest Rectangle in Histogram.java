public class Solution {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> iStack = new Stack<Integer>();
        Stack<Integer> hStack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            int last = i;
            while (!hStack.isEmpty() && height[i] <= hStack.peek()) {
                last = iStack.pop();
                max = Math.max(max, hStack.pop() * (i - last));
            }
            iStack.push(last);
            hStack.push(height[i]);
        }

        while (!iStack.isEmpty()) {
            max = Math.max(max, (height.length - iStack.pop()) * hStack.pop());
        }
        return max;
    }
}

class Solution2 {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0, i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[stack.peek()] <= height[i])
                stack.push(i++);
            else
                max = Math.max(max, height[stack.pop()] * (stack.isEmpty() ? i : i - stack.peek() - 1));
        }

        while (!stack.isEmpty())
            max = Math.max(max, height[stack.pop()]
                    * (stack.isEmpty() ? height.length : height.length - stack.peek() - 1));
        return max;
    }
}
