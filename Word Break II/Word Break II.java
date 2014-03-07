public class Solution {
	public ArrayList<String> wordBreak(String s, Set<String> dict) {
		byte[] memo = new byte[s.length() + 1];
		memo[s.length()] = 1;

		return doWordBreak(s, 0, dict, memo);
	}

	public ArrayList<String> doWordBreak(String s, int start, Set<String> dict, byte[] memo) {
		ArrayList<String> result = new ArrayList<String>();
		if (start == s.length()) {
			result.add("");
			return result;
		}

		for (int i = start; i < s.length(); i++) {
			String firstWord = s.substring(start, i + 1);
			if (dict.contains(firstWord) && (memo[i + 1]) != -1) {
				ArrayList<String> subResult = doWordBreak(s, i + 1, dict, memo);
				if (subResult.size() > 0) {
					for (String words : subResult) {
						String newWords = firstWord + (words.isEmpty() ? "" : " " + words);
						result.add(newWords);
					}
				}
			}
		}

		memo[start] = (byte) (result.size() == 0 ? -1 : 1);
		return result;
	}
}
