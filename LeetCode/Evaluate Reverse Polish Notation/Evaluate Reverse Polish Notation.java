public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stk = new Stack<Integer>();
        for (String tok : tokens) {
            if (tok.equals("+")) {
                stk.push(stk.pop() + stk.pop());
            } else if (tok.equals("-")) {
                stk.push(-stk.pop() + stk.pop());
            } else if (tok.equals("*")) {
                stk.push(stk.pop() * stk.pop());
            } else if (tok.equals("/")) {
                int divisor = stk.pop();
                stk.push(stk.pop() / divisor);
            } else {
                stk.push(Integer.parseInt(tok));
            }
        }
        return stk.pop();
    }
}
