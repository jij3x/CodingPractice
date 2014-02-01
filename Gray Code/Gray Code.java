public class Solution {
	public ArrayList<Integer> grayCode(int n) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < (1<<n); i++)
			result.add((i >> 1) ^ i);

		return result;
	}
}

class Solution2 {
	public ArrayList<Integer> grayCode(int n) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (n == 0) {
		    result.add(0);
		} else if (n == 1) {
			result.add(0);
			result.add(1);
		} else {
			ArrayList<Integer> subResult = grayCode(n - 1);
			result.addAll(subResult);
			
			for (int i = subResult.size() - 1; i >= 0; i--) {
				result.add((1 << (n - 1)) + subResult.get(i));
			}
		}

		return result;
	}
}
