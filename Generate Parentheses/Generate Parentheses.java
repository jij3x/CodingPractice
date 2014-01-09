public class Solution {
	public ArrayList<String> generateParenthesis(int n) {
		ArrayList<String> result = new ArrayList<String>();
		dfs("", n, n, result);
		return result;
	}

	private void dfs(String past, int left, int right, ArrayList<String> result) {
		if (right == 0) {
			result.add(past);
			return;
		}

		if (left > 0)
			dfs(past + "(", left - 1, right, result);
		if (right > left)
			dfs(past + ")", left, right - 1, result);
	}
}

class Solution2 {
	public ArrayList<String> generateParenthesis(int n) {
		ArrayList<String> result = new ArrayList<String>();
		if (n == 0)
			return result;
		if (n == 1) {
			result.add("()");
			return result;
		}

		ArrayList<String> subResult = generateParenthesis(n - 1);
		for (String r : subResult) {
			result.add("()" + r);

			Stack<String> workingStack = new Stack<String>();
			for (int i = 0; i < r.length(); i++) {
				if (r.charAt(i) == ')') {
					workingStack.pop();
					if (workingStack.isEmpty()) {
						result.add("(" + r.substring(0, i + 1) + ")" + r.substring(i + 1, r.length()));
					}
				} else {
					workingStack.push("(");
				}
			}
		}
		return result;
	}
}