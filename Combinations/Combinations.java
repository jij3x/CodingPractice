public class Solution {
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		if (k < 1 || k > n)
			return result;
		if (k == 1) {
			for (int i = 1; i <= n; i++) {
				ArrayList<Integer> row = new ArrayList<Integer>();
				row.add(i);
				result.add(row);
			}
			return result;
		}

		for (int i = n; i >= k; i--) {
			ArrayList<ArrayList<Integer>> subResult = combine(i - 1, k - 1);
			for (ArrayList<Integer> row : subResult) {
				row.add(i);
				result.add(row);
			}
		}
		return result;
	}
}

class Solution2 {
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		return combineRecursive(n, k, 1);
	}

	private ArrayList<ArrayList<Integer>> combineRecursive(int n, int k, int from) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (k <= 0 || k > n - (from - 1)) {
			return result;
		} else if (k == n - (from - 1)) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int i = from; i <= n; i++) {
				row.add(i);
			}
			result.add(row);
			return result;
		} else if (k == 1) {
			for (int i = from; i <= n; i++) {
				ArrayList<Integer> row = new ArrayList<Integer>();
				row.add(i);
				result.add(row);
			}
			return result;
		}

		ArrayList<ArrayList<Integer>> subResult = combineRecursive(n, k, from + 1);
		result.addAll(subResult);

		subResult = combineRecursive(n, k - 1, from + 1);
		for (int i = 0; i < subResult.size(); i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			row.add(from);
			row.addAll(subResult.get(i));
			result.add(row);
		}

		return result;
	}
}