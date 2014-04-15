public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        return dpMinDist(word1, 0, word2, 0, memo);
    }

    private int dpMinDist(String word1, int i, String word2, int j, int[][] memo) {
        if (i == word1.length() && j == word2.length())
            return 0;
        if (i == word1.length())
            return word2.length() - j;
        if (j == word2.length())
            return word1.length() - i;
        
        if (memo[i][j] != -1)
            return memo[i][j];

        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = dpMinDist(word1, i + 1, word2, j + 1, memo);
        } else {
            int replaceDist = dpMinDist(word1, i + 1, word2, j + 1, memo);
            int deleteDist = dpMinDist(word1, i + 1, word2, j, memo);
            int insertDist = dpMinDist(word1, i, word2, j + 1, memo);
            memo[i][j] = 1 + Math.min(replaceDist, Math.min(deleteDist, insertDist));
        }
        return memo[i][j];
    }
}

class Solution2 {
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++)
            memo[i][word2.length()] = word1.length() - i;
        for (int j = 0; j <= word2.length(); j++)
            memo[word1.length()][j] = word2.length() - j;

        for (int i = word1.length() - 1; i >= 0; i--) {
            for (int j = word2.length() - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j))
                    memo[i][j] = memo[i + 1][j + 1];
                else
                    memo[i][j] = Math.min(memo[i + 1][j], Math.min(memo[i][j + 1], memo[i + 1][j + 1])) + 1;
            }
        }
        return memo[0][0];
    }
}
