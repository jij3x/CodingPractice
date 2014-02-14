public class Solution {
	public int evalRPN(String[] tokens) {
		Stack<Integer> workingStack = new Stack<Integer>();
		for (int i = 0; i < tokens.length; i++) {
			int result;
			if (tokens[i].equals("+")) {
				result = workingStack.pop() + workingStack.pop();
			} else if (tokens[i].equals("-")) {
				result = workingStack.pop();
				result = workingStack.pop() - result;
			} else if (tokens[i].equals("*")) {
				result = workingStack.pop() * workingStack.pop();
			} else if (tokens[i].equals("/")) {
				result = workingStack.pop();
				result = workingStack.pop() / result;
			} else {
				result = Integer.parseInt(tokens[i]);
			}
			workingStack.push(result);
		}

		return workingStack.pop();
	}
}
