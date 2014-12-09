public class Solution {
    public int numDistinct(String S, String T) {
        int[][] memo = new int[T.length() + 1][S.length() + 1];
        Arrays.fill(memo[T.length()], 1);

        for (int x = S.length() - 1; x >= 0; x--) {
            for (int y = T.length() - 1; y >= 0; y--)
                memo[y][x] = memo[y][x + 1] + (S.charAt(x) == T.charAt(y) ? memo[y + 1][x + 1] : 0);
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

class Solution3 {
    public int numDistinct(String S, String T) {
        int[] prev = new int[T.length() + 1], curr = new int[T.length() + 1];
        prev[prev.length - 1] = curr[curr.length - 1] = 1;

        for (int i = S.length() - 1; i >= 0; i--) {
            for (int j = T.length() - 1; j >= 0; j--)
                curr[j] = prev[j] + (S.charAt(i) == T.charAt(j) ? prev[j + 1] : 0);

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[0];
    }
}
