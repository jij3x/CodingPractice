public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < tokens.length; i++) {
            int result;
            if (tokens[i].equals("+")) {
                result = stack.pop() + stack.pop();
            } else if (tokens[i].equals("-")) {
                result = stack.pop();
                result = stack.pop() - result;
            } else if (tokens[i].equals("*")) {
                result = stack.pop() * stack.pop();
            } else if (tokens[i].equals("/")) {
                result = stack.pop();
                result = stack.pop() / result;
            } else {
                result = Integer.parseInt(tokens[i]);
            }
            stack.push(result);
        }

        return stack.pop();
    }
}
