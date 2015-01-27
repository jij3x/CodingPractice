public class Solution {
    public int candy(int[] ratings) {
        int[] memo = new int[ratings.length];
        memo[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            memo[i] = ratings[i] > ratings[i - 1] ? memo[i - 1] + 1 : 1;
        }

        int sum = memo[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && memo[i] <= memo[i + 1])
                memo[i] = memo[i + 1] + 1;
            sum += memo[i];
        }
        return sum;
    }
}
