public class Solution {
    private final String[] charTbl = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public ArrayList<String> letterCombinations(String digits) {
        return doCombination(digits, 0);
    }

    private ArrayList<String> doCombination(String digits, int start) {
        ArrayList<String> result = new ArrayList<String>();
        if (start == digits.length()) {
            result.add("");
            return result;
        }

        for (int i = 0; i < charTbl[digits.charAt(start) - '0'].length(); i++) {
            for (String s : doCombination(digits, start + 1))
                result.add(charTbl[digits.charAt(start) - '0'].charAt(i) + s);
        }
        return result;
    }
}

class Solution2 {
    private final String[] keypad = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<String>();
        if (digits.length() == 0) {
            result.add("");
            return result;
        }

        String letters = keypad[(int) digits.charAt(0) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            for (String s : letterCombinations(digits.substring(1)))
                result.add(letters.charAt(i) + s);
        }
        return result;
    }
}
