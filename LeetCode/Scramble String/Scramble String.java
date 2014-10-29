public class Solution {
    public boolean isScramble(String s1, String s2) {
        byte memo[][][] = new byte[s1.length()][s2.length()][s1.length()];
        return dp(s1, 0, s2, 0, s1.length(), memo);
    }

    private boolean dp(String s1, int s1s, String s2, int s2s, int length, byte[][][] memo) {
        if (memo[s1s][s2s][length - 1] != 0)
            return memo[s1s][s2s][length - 1] == 1 ? true : false;

        memo[s1s][s2s][length - 1] = 1;
        for (int i = s1s, j = s2s; i < s1s + length; i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                memo[s1s][s2s][length - 1] = -1;
                break;
            }
        }
        if (memo[s1s][s2s][length - 1] == 1)
            return true;

        for (int i = 1, j = length - 1; i < length; i++, j--) {
            if ((dp(s1, s1s, s2, s2s, i, memo) && dp(s1, s1s + i, s2, s2s + i, j, memo))
                    || (dp(s1, s1s, s2, s2s + j, i, memo) && dp(s1, s1s + i, s2, s2s, j, memo))) {
                memo[s1s][s2s][length - 1] = 1;
                return true;
            }
        }
        return false;
    }
}

// Backtracking only
class Solution2 {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2))
            return true;

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i])
                return false;
        }

        for (int i = 1, j = s1.length() - 1; i < s1.length(); i++, j--) {
            if ((isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)))
                    || (isScramble(s1.substring(0, i), s2.substring(j)) && isScramble(s1.substring(i), s2.substring(0, j))))
                return true;
        }
        return false;
    }
}

class Solution3 {
    public boolean isScramble(String s1, String s2) {
        boolean[][][] memo = new boolean[s1.length()][s1.length()][s1.length()];
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s1.length() - 1; j >= 0; j--) {
                memo[i][j][0] = s1.charAt(i) == s2.charAt(j);
                for (int len = 2; len <= Math.min(s1.length() - i, s1.length() - j); len++) {
                    for (int m = 1, n = len - m; m < len; m++, n--) {
                        if ((memo[i][j][m - 1] && memo[i + m][j + m][n - 1])
                                || (memo[i][j + n][m - 1] && memo[i + m][j][n - 1])) {
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
