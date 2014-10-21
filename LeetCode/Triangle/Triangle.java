public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] memo = new int[triangle.size() + 1];
        for (int y = triangle.size() - 1; y >= 0; y--) {
            for (int x = 0; x <= y; x++)
                memo[x] = Math.min(memo[x], memo[x + 1]) + triangle.get(y).get(x);
        }
        return memo[0];
    }
}
