public class Solution {
    public int maxProduct(int A[]) {
        int max = A[0], maxEnd = A[0], minEnd = A[0];
        for (int i = 1; i < A.length; i++) {
            int end1 = maxEnd * A[i], end2 = minEnd * A[i];
            maxEnd = Math.max(Math.max(end1, end2), A[i]);
            minEnd = Math.min(Math.min(end1, end2), A[i]);

            max = Math.max(max, maxEnd);
        }
        return max;
    }
}

class Solution2 {
    public int maxProduct(int A[]) {
        int max = A[0];
        int maxPos = A[0] > 0 ? A[0] : 0;
        int minNeg = A[0] < 0 ? A[0] : 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > 0) {
                maxPos = maxPos > 0 ? maxPos * A[i] : A[i];
                minNeg = minNeg < 0 ? minNeg * A[i] : 0;
            } else if (A[i] < 0) {
                int tempMax = minNeg < 0 ? minNeg * A[i] : 0;
                minNeg = maxPos > 0 ? maxPos * A[i] : A[i];
                maxPos = tempMax;
            } else {
                maxPos = minNeg = 0;
            }

            max = Math.max(max, maxPos);
        }
        return max;
    }
}
