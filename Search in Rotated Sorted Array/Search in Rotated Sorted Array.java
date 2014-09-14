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

class Solution3 {
    public int search(int[] A, int target) {
        int minIndex = findMin(A);
        int left, right;
        if (target >= A[minIndex] && target <= A[A.length - 1]) {
            left = minIndex;
            right = A.length - 1;
        } else {
            left = 0;
            right = minIndex - 1;
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target)
                return mid;

            if (target > A[mid])
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    private int findMin(int[] A) {
        int left = 0, right = A.length - 1;
        while (A[left] > A[right]) {
            int mid = (left + right) / 2;
            if (A[mid] > A[right])
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}
