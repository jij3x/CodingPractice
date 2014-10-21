public class Solution {
    private int kadan(int[] A) {
        int maxSoFar = A[0], maxEndingHere = 0;
        for (int x : A) {
            maxEndingHere = Math.max(0, maxEndingHere + x);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public int maxSubArray(int[] A) {
        int unwrapMax = kadan(A);

        int wrapMax = 0;
        for (int i = 0; i < A.length; i++) {
            wrapMax += A[i];
            A[i] = -A[i];
        }
        wrapMax += kadan(A);

        return Math.max(unwrapMax, wrapMax);
    }
}