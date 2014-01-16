public class Solution {
	public int maxSubArray(int[] A) {
		if (A.length == 0)
			return 0;

		int prevMax = A[0];
		int overallMax = A[0];
		for (int i = 1; i  A.length; i++) {
			prevMax = Math.max(prevMax + A[i], A[i]);
			overallMax = Math.max(overallMax, prevMax);
		}
		return overallMax;
	}
}
