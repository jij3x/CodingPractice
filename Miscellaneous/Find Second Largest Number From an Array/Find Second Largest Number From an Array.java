public class Solution {
    public int findSecondLargest(int[] A) {
        if (A.length <= 1)
            return Integer.MAX_VALUE;

        int fstLargest = Math.max(A[0], A[1]);
        int secLargest = Math.min(A[0], A[1]);

        for (int i = 2; i < A.length; i++) {
            if (A[i] > secLargest) {
                secLargest = A[i];
                if (fstLargest < secLargest) {
                    int temp = fstLargest;
                    fstLargest = secLargest;
                    secLargest = temp;
                }
            }
        }
        return secLargest;
    }
}
