public class Solution {
    public boolean search(int[] A, int target) {
        int start = 0, end = A.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target)
                return true;

            if (A[mid] > A[end]) {
                if (A[start] <= target && A[mid] > target)
                    end = mid - 1;
                else
                    start = mid + 1;
            } else if (A[mid] < A[end]) {
                if (A[mid] < target && A[end] >= target)
                    start = mid + 1;
                else
                    end = mid - 1;
            } else {
                end--;
            }
        }
        return false;
    }
}
