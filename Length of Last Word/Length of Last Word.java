public class Solution {
	public int lengthOfLastWord(String s) {
		int i = s.length() - 1;
		while (i >= 0) {
			if (s.charAt(i) != ' ')
				break;
			i--;
		}

		int ret = 0;
		while (i >= 0) {
			if (s.charAt(i) == ' ')
				return ret;

			ret++;
			i--;
		}

		return ret;
	}
}
