public class Solution {
    private final String[] pad = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        return dfs(digits, 0);
    }

    private List<String> dfs(String digits, int start) {
        ArrayList<String> result = new ArrayList<String>();
        if (start == digits.length()) {
            result.add("");
            return result;
        }

        for (int i = 0, num = digits.charAt(start) - '0'; i < pad[num].length(); i++) {
            for (String r : dfs(digits, start + 1))
                result.add(pad[num].charAt(i) + r);
        }
        return result;
    }
}

class Solution2 {
    public List<String> letterCombinations(String digits) {
        final String[] pad = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        ArrayList<String> result = new ArrayList<String>();
        if (digits.isEmpty()) {
            result.add("");
            return result;
        }

        for (int i = 0, num = digits.charAt(0) - '0'; i < pad[num].length(); i++) {
            for (String r : letterCombinations(digits.substring(1)))
                result.add(pad[num].charAt(i) + r);
        }
        return result;
    }
}
