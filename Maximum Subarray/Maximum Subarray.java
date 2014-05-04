public class Solution {
    public int maxSubArray(int[] A) {
        int max = A[0];
        for (int i = 0, prev = 0; i < A.length; i++) {
            prev += A[i];
            max = Math.max(max, prev);
            prev = Math.max(prev, 0);
        }
        return max;
    }
}
