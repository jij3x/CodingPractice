public class Solution {
    public String longestPalindrome(String s) {
        int maxPos = 0, maxLen = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            int len = plinAround(s, i, i);
            if (len > maxLen) {
                maxLen = len;
                maxPos = i - (len - 1) / 2;
            }
            len = plinAround(s, i, i + 1);
            if (len > maxLen) {
                maxLen = len;
                maxPos = i - (len / 2 - 1);
            }
        }
        return s.substring(maxPos, maxPos + maxLen);
    }

    private int plinAround(String s, int left, int right) {
        int len = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            len = right++ - left-- + 1;
        }
        return len;
    }
}

class Solution2 {
    public String longestPalindrome(String s) {
        boolean[][] memo = new boolean[s.length()][s.length()];
        int maxLen = 0, maxPos = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (j + 1 >= i - 1 || memo[j + 1][i - 1])) {
                    memo[j][i] = true;
                    if (i - j + 1 > maxLen) {
                        maxLen = i - j + 1;
                        maxPos = j;
                    }
                }
            }
        }
        return s.substring(maxPos, maxPos + maxLen);
    }
}
