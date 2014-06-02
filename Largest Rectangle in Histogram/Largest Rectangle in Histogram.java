public class Solution {
    public int largestRectangleArea(int[] height) {
        if (height.length == 0)
            return 0;

        Stack<Integer> hStack = new Stack<Integer>();
        Stack<Integer> iStack = new Stack<Integer>();
        int max = 0;

        hStack.push(height[0]);
        iStack.push(0);
        for (int i = 1; i < height.length; i++) {
            if (height[i] < hStack.peek()) {
                int last = 0;
                while (!hStack.isEmpty() && hStack.peek() > height[i]) {
                    last = iStack.pop();
                    max = Math.max(max, (i - last) * hStack.pop());
                }
                hStack.push(height[i]);
                iStack.push(iStack.isEmpty() ? 0 : last);
            } else if (height[i] > hStack.peek()) {
                hStack.push(height[i]);
                iStack.push(i);
            }
        }

        while (!hStack.isEmpty())
            max = Math.max(max, hStack.pop() * (height.length - iStack.pop()));

        return max;
    }
}

class Solution2 {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i < height.length;) {
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
