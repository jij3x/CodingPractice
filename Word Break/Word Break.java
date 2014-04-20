public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        byte[] memo = new byte[s.length()];
        return dpWordBreak(s, dict, 0, memo);
    }

    private boolean dpWordBreak(String s, Set<String> dict, int start, byte[] memo) {
        if (start >= s.length())
            return true;
        if (memo[start] != 0)
            return memo[start] == 1 ? true : false;

        for (int i = start; i < s.length(); i++) {
            if (dict.contains(s.substring(start, i + 1)) && dpWordBreak(s, dict, i + 1, memo)) {
                memo[start] = 1;
                return true;
            }
        }

        memo[start] = -1;
        return false;
    }
}

class Solution2 {
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] memo = new boolean[s.length() + 1];
        memo[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (memo[j] && dict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[s.length()];
    }
}
