public class Solution {
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		return subSubsets(S, 0);
	}

	private ArrayList<ArrayList<Integer>> subSubsets(int[] S, int start) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> emptyRow = new ArrayList<Integer>();
		result.add(emptyRow);
		
		if (start >= S.length)
			return result;

		for (int i = start; i < S.length; i++) {
			ArrayList<ArrayList<Integer>> subResult = subSubsets(S, i + 1);
			for (ArrayList<Integer> row : subResult) {
				row.add(S[i]);
				Collections.sort(row);
				result.add(row);
			}
		}
		return result;
	}
}

class Solution2 {
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < S.length; i++) {
			int j = result.size() - 1;
			while (j >= 0) {
				ArrayList<Integer> row = new ArrayList<Integer>(result.get(j));
				row.add(S[i]);
				result.add(row);
				j--;
			}

			ArrayList<Integer> row = new ArrayList<Integer>();
			row.add(S[i]);
			result.add(row);
		}

		result.add(new ArrayList<Integer>());
		return result;
	}
}