public class Solution {
    public int maxDist(int[] A) {
        if (A.length == 0)
            return 0;

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for (int i = 1; i < A.length; i++) {
            if (A[i] < stack.peek())
                stack.push(i);
        }

        int max = 0, j = A.length - 1;
        while (!stack.isEmpty()) {
            if (j == stack.peek())
                stack.pop();
            else if (A[j] > A[stack.peek()])
                max = Math.max(max, j - stack.pop());
            else
                j--;
        }
        return max;
    }
}