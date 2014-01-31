public class Solution {
	public ArrayList<String> restoreIpAddresses(String s) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> path = new ArrayList<String>();
		dfs(s, 0, 1, path, result);
		return result;
	}

	private void dfs(String s, int start, int level, ArrayList<String> path, ArrayList<String> result) {
		if (level == 5) {
			if (start == s.length()) {
				String ip = "";
				for (String segment : path)
					ip += "." + segment;
				result.add(ip.substring(1));
			}
			return;
		}

		for (int i = start; i < start + 3 && i < s.length(); i++) {
			String segment = s.substring(start, i + 1);
			if ((segment.length() > 1 && segment.charAt(0) == '0') || Integer.parseInt(segment) > 255)
				break;

			path.add(segment);
			dfs(s, i + 1, level + 1, path, result);
			path.remove(path.size() - 1);
		}
	}
}

class Solution2 {
	public ArrayList<String> restoreIpAddresses(String s) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i <= 3 && i < s.length(); i++) {
			String first = s.substring(0, i + 1);
			if (isInvalid(first))
				break;
			for (int j = i + 1; j <= i + 3 && j < s.length(); j++) {
				String second = s.substring(i + 1, j + 1);
				if (isInvalid(second))
					break;
				for (int k = j + 1; k <= j + 3 && k < s.length(); k++) {
					String third = s.substring(j + 1, k + 1);
					if (isInvalid(third))
						break;

					if (k + 1 >= s.length())
						break;
					String fourth = s.substring(k + 1);
					if (fourth.length() <= 3 && !isInvalid(fourth))
						result.add(first + '.' + second + '.' + third + '.' + fourth);
				}
			}
		}

		return result;
	}

	private boolean isInvalid(String s) {
		return (s.length() > 1 && s.charAt(0) == '0') || Integer.parseInt(s) > 255;
	}
}