public class Solution {
	public ArrayList<Integer> getRow(int rowIndex) {
		ArrayList<Integer> currRow = new ArrayList<Integer>();
		ArrayList<Integer> prevRow = currRow;
		currRow.add(1);
		for (int i = 1; i <= rowIndex; i++) {
			currRow = new ArrayList<Integer>();
			currRow.add(1);
			for (int j = 1; j < i; j++) {
				currRow.add(prevRow.get(j - 1) + prevRow.get(j));
			}
			if (i > 0)
				currRow.add(1);
			prevRow = currRow;
		}

		return currRow;
	}
}

class Solution2 {
	public ArrayList<Integer> getRow(int rowIndex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i <= rowIndex; result.add(1), i++)
			;
		for (int i = 1; i <= rowIndex; i++) {
			result.set(i, 1);
			for (int j = i - 1; j >= 1; j--)
				result.set(j, result.get(j) + result.get(j - 1));
		}
		return result;
	}
}
