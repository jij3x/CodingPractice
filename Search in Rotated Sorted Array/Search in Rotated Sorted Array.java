public class Solution {
	public int search(int[] A, int target) {
		int left = 0;
		int right = A.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (A[mid] == target)
				return mid;

			if (A[mid] < A[right]) {
				if (target < A[mid] || target > A[right])
					right = mid - 1;
				else
					left = mid + 1;
			} else {
				if (target > A[mid] || target < A[left])
					left = mid + 1;
				else
					right = mid - 1;
			}
		}
		return -1;
	}
}

class Solution2 {
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
			int mid = left + (right - left) / 2;
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
		int left = 0;
		int right = A.length - 1;
		while (A[left] > A[right]) {
			int mid = left + (right - left) / 2;
			if (A[mid] > A[right])
				left = mid + 1;
			else
				right = mid;
		}
		return left;
	}
}
