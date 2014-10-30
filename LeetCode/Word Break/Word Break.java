public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        byte[] memo = new byte[s.length()];
        return dpWordBreak(s, 0, dict, memo);
    }

    public boolean dpWordBreak(String s, int start, Set<String> dict, byte[] memo) {
        if (start == s.length())
            return true;
        if (memo[start] != 0)
            return memo[start] == 1 ? true : false;

        for (int i = start; i < s.length(); i++) {
            if (dict.contains(s.substring(start, i + 1)) && dpWordBreak(s, i + 1, dict, memo)) {
                memo[start] = 1;
                return true;
            }
        }
        memo[start] = -1;
        return false;
    }
}

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] memo = new boolean[s.length() + 1];
        memo[s.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (dict.contains(s.substring(i, j + 1)) && memo[j + 1]) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[0];
    }
}
