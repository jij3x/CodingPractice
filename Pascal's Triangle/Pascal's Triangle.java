public class Solution {
	public ArrayList<ArrayList<Integer>> generate(int numRows) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (numRows == 0)
			return result;

		for (int i = 0; i < numRows; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					row.add(1);
				} else {
					int thisNum = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
					row.add(thisNum);
				}
			}
			result.add(row);
		}
		return result;
	}
}
