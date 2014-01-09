public class Solution {
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (k > n) {
			return result;
		} else if (k == 1) { // base case needs to be on (k=1), rather than (k=n), to prevent k go below 0
			for (int i = 1; i <= n; i++) {
				ArrayList<Integer> row = new ArrayList<Integer>();
				row.add(i);
				result.add(row);
			}
			return result;
		}

		ArrayList<ArrayList<Integer>> subResult = combine(n - 1, k - 1);
		for (ArrayList<Integer> row : subResult) {
			row.add(n);
			Collections.sort(row);
			result.add(row);
		}

		subResult = combine(n - 1, k);
		result.addAll(subResult);

		return result;
	}
}