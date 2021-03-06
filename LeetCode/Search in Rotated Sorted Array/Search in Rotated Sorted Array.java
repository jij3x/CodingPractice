public class Solution {
    public int search(int[] A, int target) {
        for (int start = 0, end = A.length - 1, mid = 0; start <= end;) {
            mid = (start + end) / 2;
            if (A[mid] == target)
                return mid;

            if (A[mid] < A[end]) {
                if (target > A[mid] && target <= A[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            } else {
                if (target >= A[start] && target < A[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
        }
        return -1;
    }
}

class Solution2 {
    public int search(int[] A, int target) {
        for (int start = 0, end = A.length - 1, mid = 0; start <= end;) {
            mid = (start + end) / 2;
            if (A[mid] == target)
                return mid;

            if (A[start] <= A[mid]) {
                if (target >= A[start] && target < A[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            } else {
                if (target > A[mid] && target <= A[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
