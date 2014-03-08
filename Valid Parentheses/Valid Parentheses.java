public class Solution {
	public boolean isValid(String s) {
		Stack<Character> workingStack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')' && (workingStack.isEmpty() || workingStack.pop() != '(')) {
				return false;
			} else if (s.charAt(i) == ']' && (workingStack.isEmpty() || workingStack.pop() != '[')) {
				return false;
			} else if (s.charAt(i) == '}' && (workingStack.isEmpty() || workingStack.pop() != '{')) {
				return false;
			} else {
				workingStack.push(s.charAt(i));
			}
		}

		if (workingStack.size() > 0)
			return false;

		return true;
	}
}