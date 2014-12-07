public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        boolean[][] memo = new boolean[s1.length() + 1][s2.length() + 1];
        memo[s1.length()][s2.length()] = true;
        for (int i = s1.length() - 1; i >= 0; i--)
            memo[i][s2.length()] = s1.charAt(i) == s3.charAt(s2.length() + i) && memo[i + 1][s2.length()];
        for (int i = s2.length() - 1; i >= 0; i--)
            memo[s1.length()][i] = s2.charAt(i) == s3.charAt(s1.length() + i) && memo[s1.length()][i + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--)
                memo[i][j] = (s1.charAt(i) == s3.charAt(i + j) && memo[i + 1][j])
                        || (s2.charAt(j) == s3.charAt(i + j) && memo[i][j + 1]);
        }
        return memo[0][0];
    }
}

class Solution2 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        byte[][] memo = new byte[s1.length()][s2.length()];
        return dp(s1, 0, s2, 0, s3, 0, memo);
    }

    private boolean dp(String s1, int s1i, String s2, int s2i, String s3, int s3i, byte[][] memo) {
        if (s1i == s1.length() || s2i == s2.length())
            return s1i == s1.length() ? s2.substring(s2i).equals(s3.substring(s3i)) : 
                s1.substring(s1i).equals(s3.substring(s3i));
        if (memo[s1i][s2i] != 0)
            return memo[s1i][s2i] == 1 ? true : false;

        if ((s1.charAt(s1i) == s3.charAt(s3i) && dp(s1, s1i + 1, s2, s2i, s3, s3i + 1, memo))
                || (s2.charAt(s2i) == s3.charAt(s3i) && dp(s1, s1i, s2, s2i + 1, s3, s3i + 1, memo)))
            memo[s1i][s2i] = 1;
        else
            memo[s1i][s2i] = -1;
        return memo[s1i][s2i] == 1 ? true : false;
    }
}
