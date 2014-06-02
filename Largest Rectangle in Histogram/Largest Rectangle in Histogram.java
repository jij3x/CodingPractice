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

/*
 * Hard to explain. Not suitable for special event.
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
