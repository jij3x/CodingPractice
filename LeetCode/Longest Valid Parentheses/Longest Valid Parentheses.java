public class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stk = new Stack<Integer>();
        int max = 0;
        for (int i = 0, last = -1; i < s.length(); i++) {
            if (s.charAt(i) == ')' && stk.isEmpty()) {
                last = i;
            } else if (s.charAt(i) == ')') {
                stk.pop();
                max = Math.max(max, stk.isEmpty() ? i - last : i - stk.peek());
            } else {
                stk.push(i);
            }
        }
        return max;
    }
}
