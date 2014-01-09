public class Solution {
	public ArrayList<String> letterCombinations(String digits) {
		String[] keypad = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

		ArrayList<String> result = new ArrayList<String>();
		if (digits.length() == 0) {
			result.add("");
			return result;
		}

		String letters = keypad[(int) digits.charAt(0) - (int) '0'];
		for (int i = 0; i < letters.length(); i++) {
			ArrayList<String> subResult = letterCombinations(digits.substring(1));
			for (String s : subResult)
				result.add(letters.charAt(i) + s);
		}
		return result;
	}
}