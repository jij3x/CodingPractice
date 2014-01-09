public class Solution {
	public boolean search(int[] A, int target) {
		int left = 0;
		int right = A.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (A[mid] == target)
				return true;

			if (A[mid] < A[right]) {
				// 4,5,5,5,5,6,0,(1),1,1,1,1,2,3
				if (target < A[mid] || target > A[right])
					right = mid - 1;
				else
					left = mid + 1;
			} else if (A[mid] > A[right]) {
				// 4,5,5,5,(5),6,0,1,1,1,1,1,2,3
				if (target > A[mid] || target < A[left])
					left = mid + 1;
				else
					right = mid - 1;
			} else {
				right--;
			}
		}
		return false;
	}
}