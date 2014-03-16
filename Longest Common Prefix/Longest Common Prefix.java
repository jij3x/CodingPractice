public class Solution {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";

		int end = 0;
		done: while (true) {
			for (int i = 0; i < strs.length; i++) {
				if (strs[i].length() == end || (i != 0 && strs[i].charAt(end) != strs[i - 1].charAt(end)))
					break done;
			}
			end++;
		}
		return strs[0].substring(0, end);
	}
}
