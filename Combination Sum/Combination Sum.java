public class Solution {
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
		return subCombinationSum(candidates, 0, target);
	}

	public ArrayList<ArrayList<Integer>> subCombinationSum(int[] candidates, int start, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (int i = start; i < candidates.length; i++) {
			if (candidates[i] == target) {
				ArrayList<Integer> row = new ArrayList<Integer>();
				row.add(candidates[i]);
				result.add(row);
			} else if (candidates[i] < target) {
				ArrayList<ArrayList<Integer>> subResult = subCombinationSum(candidates, i, target - candidates[i]);
				for (ArrayList<Integer> row : subResult) {
					row.add(candidates[i]);
					Collections.sort(row);
					result.add(row);
				}
			}
		}

		return result;
	}
}