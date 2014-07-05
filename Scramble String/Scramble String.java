public class Solution {
    public boolean isScramble(String s1, String s2) {
        byte memo[][][] = new byte[s1.length()][s2.length()][s1.length()];
        return dp(s1, 0, s2, 0, s1.length(), memo);
    }

    private boolean dp(String s1, int s1s, String s2, int s2s, int length, byte[][][] memo) {
        if (memo[s1s][s2s][length - 1] != 0)
            return memo[s1s][s2s][length - 1] == 1 ? true : false;

        boolean identical = true;
        for (int i = s1s, j = s2s; i < s1s + length; i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                identical = false;
                break;
            }
        }
        if (identical) {
            memo[s1s][s2s][length - 1] = 1;
            return true;
        }

        for (int i = 1, j = length - 1; i < length; i++, j--) {
            if ((dp(s1, s1s, s2, s2s, i, memo) && dp(s1, s1s + i, s2, s2s + i, j, memo))
                    || (dp(s1, s1s, s2, s2s + j, i, memo) && dp(s1, s1s + i, s2, s2s, j, memo))) {
                memo[s1s][s2s][length - 1] = 1;
                return true;
            }
        }
        memo[s1s][s2s][length - 1] = -1;
        return false;
    }
}

// Backtracking only
class Solution2 {
    public boolean isScramble(String s1, String s2) {
        return bt(s1, 0, s2, 0, s1.length());
    }

    private boolean bt(String s1, int s1s, String s2, int s2s, int length) {
        if (isIdentical(s1, s1s, s2, s2s, length))
            return true;
        if (!sameCharSet(s1, s1s, s2, s2s, length))
            return false;

        for (int i = 1, j = length - 1; i < length; i++, j--) {
            if ((bt(s1, s1s, s2, s2s, i) && bt(s1, s1s + i, s2, s2s + i, j))
                    || (bt(s1, s1s, s2, s2s + j, i) && bt(s1, s1s + i, s2, s2s, j)))
                return true;
        }
        return false;
    }

    private boolean isIdentical(String s1, int s1s, String s2, int s2s, int length) {
        for (int i = s1s, j = s2s; i < s1s + length; i++, j++) {
            if (s1.charAt(i) != s2.charAt(j))
                return false;
        }
        return true;
    }

    private boolean sameCharSet(String s1, int s1s, String s2, int s2s, int length) {
        char[] charSet1 = s1.substring(s1s, s1s + length).toCharArray();
        char[] charSet2 = s2.substring(s2s, s2s + length).toCharArray();
        Arrays.sort(charSet1);
        Arrays.sort(charSet2);
        for (int i = 0; i < length; i++) {
            if (charSet1[i] != charSet2[i])
                return false;
        }
        return true;
    }
}

class Solution3 {
    public boolean isScramble(String s1, String s2) {
        boolean memo[][][] = new boolean[s1.length()][s2.length()][s1.length()];
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                memo[i][j][0] = s1.charAt(i) == s2.charAt(j);
                for (int len = 2; len <= Math.min(s1.length() - i, s2.length() - j); len++) {
                    for (int m = 1, n = len - 1; m < len; m++, n--) {
                        if ((memo[i][j][m - 1] && memo[i + m][j + m][n - 1])
                                || (memo[i][j + m][n - 1] && memo[i + n][j][m - 1])) {
                            memo[i][j][len - 1] = true;
                            break;
                        }
                    }
                }
            }
        }
        return memo[0][0][s1.length() - 1];
    }
}
