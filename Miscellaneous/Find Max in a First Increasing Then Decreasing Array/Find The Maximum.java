public class Solution {
    public int findMaximum(int[] A) {
        for (int left = 0, right = A.length - 1, mid = 0; left <= right;) {
            mid = left + (right - left) / 2;
            if ((mid == A.length - 1 || A[mid] > A[mid + 1]) && (mid == 0 || A[mid] > A[mid - 1])) {
                return A[mid];
            } else if (mid == 0 || A[mid] > A[mid - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }
}