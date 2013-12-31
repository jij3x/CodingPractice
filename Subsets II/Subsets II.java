public class Solution {
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		Arrays.sort(num);
		return subsetsWithDupRecursive(num, 0);
	}

	private ArrayList<ArrayList<Integer>> subsetsWithDupRecursive(int[] num, int start) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> emptyRow = new ArrayList<Integer>();
		result.add(emptyRow);
		if (start >= num.length)
			return result;

		for (int i = start; i < num.length; i++) {
			ArrayList<ArrayList<Integer>> subResult = subsetsWithDupRecursive(num, i + 1);
			for (ArrayList<Integer> row : subResult) {
				row.add(num[i]);
				Collections.sort(row);
				result.add(row);
			}

			while (i <= num.length - 2 && num[i] == num[i + 1])
				i++;
		}
		return result;
	}
}