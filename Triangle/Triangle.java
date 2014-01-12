public class Solution {
	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
		int[] memo = new int[triangle.size() + 1];
		for (int i = triangle.size() - 1; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				memo[j] = Math.min(memo[j], memo[j + 1]) + triangle.get(i).get(j);
			}
		}

		return memo[0];
	}
}
