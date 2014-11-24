public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        return dpMinDist(word1, 0, word2, 0, memo);
    }

    private int dpMinDist(String word1, int s1, String word2, int s2, int[][] memo) {
        if (s1 == word1.length() || s2 == word2.length())
            return Math.max(word1.length() - s1, word2.length() - s2);
        if (word1.charAt(s1) == word2.charAt(s2))
            return dpMinDist(word1, s1 + 1, word2, s2 + 1, memo);
        if (memo[s1][s2] != -1)
            return memo[s1][s2];

        int insDist = 1 + dpMinDist(word1, s1, word2, s2 + 1, memo);
        int delDist = 1 + dpMinDist(word1, s1 + 1, word2, s2, memo);
        int rpDist = 1 + dpMinDist(word1, s1 + 1, word2, s2 + 1, memo);
        return memo[s1][s2] = Math.min(insDist, Math.min(delDist, rpDist));
    }
}

class Solution2 {
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0, j = word1.length(); i < word1.length(); i++, j--)
            memo[i][word2.length()] = j;
        for (int i = 0, j = word2.length(); i < word2.length(); i++, j--)
            memo[word1.length()][i] = j;

        for (int i = word1.length() - 1; i >= 0; i--) {
            for (int j = word2.length() - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j))
                    memo[i][j] = memo[i + 1][j + 1];
                else
                    memo[i][j] = Math.min(Math.min(1 + memo[i + 1][j], 1 + memo[i][j + 1]), 1 + memo[i + 1][j + 1]);
            }
        }
        return memo[0][0];
    }
}

class Solution3 {
    public int minDistance(String word1, String word2) {
        int[][] repo = new int[2][word2.length() + 1];
        int[] prev = repo[0];
        int[] curr = repo[1];

        for (int j = 0; j <= word2.length(); j++)
            prev[j] = word2.length() - j;

        for (int i = word1.length() - 1; i >= 0; i--) {
            curr[word2.length()] = word1.length() - i;
            for (int j = word2.length() - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j))
                    curr[j] = prev[j + 1];
                else
                    curr[j] = Math.min(prev[j], Math.min(curr[j + 1], prev[j + 1])) + 1;
            }

            prev = prev == repo[0] ? repo[1] : repo[0];
            curr = curr == repo[0] ? repo[1] : repo[0];
        }
        return prev[0];
    }
}
