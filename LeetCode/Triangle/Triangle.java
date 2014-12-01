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

class Solution2 {
    public int minimumTotal(List<List<Integer>> triangle) {
        Integer[] curr = new Integer[triangle.size()], prev = new Integer[triangle.size()];
        prev[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            curr[0] = prev[0] + triangle.get(i).get(0);
            curr[i] = prev[i - 1] + triangle.get(i).get(i);
            for (int j = 1; j < i; j++)
                curr[j] = Math.min(prev[j - 1], prev[j]) + triangle.get(i).get(j);

            Integer[] temp = curr;
            curr = prev;
            prev = temp;
        }
        return Collections.min(Arrays.asList(prev));
    }
}
