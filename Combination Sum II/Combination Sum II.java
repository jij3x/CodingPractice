public class Solution {
	public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
		Arrays.sort(num);
		return subCombinationSum(num, 0, target);
	}

	private ArrayList<ArrayList<Integer>> subCombinationSum(int[] num, int start, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (int i = start; i < num.length; i++) {
			if (num[i] == target) {
				ArrayList<Integer> row = new ArrayList<Integer>();
				row.add(num[i]);
				result.add(row);
			} else if (num[i] < target) {
				ArrayList<ArrayList<Integer>> subResult = new ArrayList<ArrayList<Integer>>();
				subResult = subCombinationSum(num, i + 1, target - num[i]);
				for (ArrayList<Integer> row : subResult) {
					row.add(num[i]);
					Collections.sort(row);
					result.add(row);
				}
			}

			while (i < num.length - 1 && num[i] == num[i + 1])
				i++;
		}

		return result;
	}
}
