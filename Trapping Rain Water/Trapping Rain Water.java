public class Solution {
	public int trap(int[] A) {
		if (A.length == 0)
			return 0;

		int[] leftPeak = new int[A.length];
		leftPeak[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			leftPeak[i] = Math.max(leftPeak[i - 1], A[i]);
		}

		int[] rightPeak = new int[A.length];
		rightPeak[A.length - 1] = A[A.length - 1];
		for (int i = A.length - 2; i >= 0; i--) {
			rightPeak[i] = Math.max(rightPeak[i - 1], A[i]);
		}

		int sum = 0;
		for (int i = 1; i < A.length - 1; i++) {
			sum += Math.min(leftPeak[i], rightPeak[i]) - A[i];
		}
		return sum;
	}
}
