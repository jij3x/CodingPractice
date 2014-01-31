public class Solution {
	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;

		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
		int start = 0, end = 0, max = 0;
		while (end < s.length()) {
			if (!charMap.containsKey(s.charAt(end))) {
				max = Math.max(max, end - start + 1);
				charMap.put(s.charAt(end), end);
				end++;
			} else {
				start = charMap.get(s.charAt(end)) + 1;
				end = start;
				charMap.clear();
			}
		}

		return max;
	}
}
