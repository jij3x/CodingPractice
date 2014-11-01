public class Solution {
    public int numDistinct(String S, String T) {
        int[][] memo = new int[S.length() + 1][T.length() + 1];
        for (int i = 0; i <= S.length(); i++)
            memo[i][T.length()] = 1;

        for (int y = S.length() - 1; y >= 0; y--) {
            for (int x = T.length() - 1; x >= 0; x--)
                memo[y][x] = memo[y + 1][x] + (S.charAt(y) == T.charAt(x) ? memo[y + 1][x + 1] : 0);
        }
        return memo[0][0];
    }
}

// Stack will overflow if given String is very long.
class Solution2 {
    public int numDistinct(String S, String T) {
        int[][] memo = new int[S.length()][T.length()];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        return dp(S, 0, T, 0, memo);
    }

    private int dp(String S, int i, String T, int j, int[][] memo) {
        if (j == T.length())
            return 1;
        if (i == S.length())
            return 0;

        if (memo[i][j] == -1)
            memo[i][j] = (S.charAt(i) == T.charAt(j) ? dp(S, i + 1, T, j + 1, memo) : 0) + dp(S, i + 1, T, j, memo);
        return memo[i][j];
    }
}
