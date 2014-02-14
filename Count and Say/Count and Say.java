public class Solution {
	public String countAndSay(int n) {
		String result = "1";
		for (int i = 1; i < n; i++) {
			result = convert(result);
		}
		return result;
	}

	private String convert(String input) {
		String output = "";
		for (int i = 0; i < input.length();) {
			int count = 0, j;
			for (j = i; j < input.length(); j++) {
				if (input.charAt(j) == input.charAt(i))
					count++;
				else
					break;
			}
			output += Integer.toString(count) + input.charAt(i);
			i = j;
		}
		return output;
	}
}

class Solution2 {
	public String countAndSay(int n) {
		String result = "1";

		for (int i = 1; i < n; i++) {
			String newString = "";
			for (int j = 0, c = 0; j < result.length(); j++) {
				c++;
				if (j == result.length() - 1 || result.charAt(j) != result.charAt(j + 1)) {
					newString += Integer.toString(c) + result.charAt(j);
					c = 0;
				}
			}
			result = newString;
		}

		return result;
	}
}
