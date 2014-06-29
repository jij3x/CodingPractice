public class Solution {
    public int longestValidParentheses(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0, last = -1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    last = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty())
                        max = Math.max(max, i - last);
                    else
                        max = Math.max(max, i - stack.peek());
                }
            } else {
                stack.push(i);
            }
        }
        return max;
    }
}
