public class Solution {
	public String simplifyPath(String path) {
		Stack<StringBuilder> workingStack = new Stack<StringBuilder>();
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '/')
				continue;

			StringBuilder directory = new StringBuilder("");
			while (i < path.length() && path.charAt(i) != '/') {
				directory.append(path.charAt(i++));
			}
			if (directory.toString().equals("..")) {
				if (!workingStack.isEmpty())
					workingStack.pop();
			} else if (!directory.toString().equals(".")) {
				workingStack.push(directory);
			}
		}

		StringBuilder result = new StringBuilder("");
		while (!workingStack.isEmpty()) {
			result.insert(0, '/').insert(1, workingStack.pop());
		}
		return result.length() == 0 ? "/" : result.toString();
	}
}

class Solution2 {
	public String simplifyPath(String path) {
		Stack<String> workingStack = new Stack<String>();
		String[] directories = path.trim().split("/");
		for (String d : directories) {
			if (d.equals("..")) {
				if (workingStack.size() > 0)
					workingStack.pop();
			} else if (!d.isEmpty() && !d.equals(".")) {
				workingStack.push(d);
			}
		}

		StringBuilder result = new StringBuilder("");
		while (!workingStack.isEmpty()) {
			result.insert(0, '/').insert(1, workingStack.pop());
		}
		return result.length() == 0 ? "/" : result.toString();
	}
}
