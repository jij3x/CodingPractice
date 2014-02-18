public class Solution {
	public int maxSubArray(int[] A) {
		int max = A[0], sum = A[0];
		for (int i = 1; i < A.length; i++) {
			sum = Math.max(sum + A[i], A[i]);
			max = Math.max(max, sum);
		}
		return max;
	}
}
