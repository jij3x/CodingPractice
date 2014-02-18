public class Solution {
	public int numTrees(int n) {
		if (n == 0)
			return 1;

		int total = 0;
		for (int i = 0; i < n; i++) {
			int leftTotal = numTrees(i);
			int rightTotal = numTrees(n - 1 - i);
			total += leftTotal * rightTotal;
		}

		return total;
	}
}
