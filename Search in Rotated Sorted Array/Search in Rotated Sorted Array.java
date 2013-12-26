public class Solution {
	public int search(int[] A, int target) {
		int left = 0;
		int right = A.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (A[mid] == target)
				return mid;

			if (A[mid] < A[right]) {
				// 5,6,7,0,(1),2,3,4
				if (target < A[mid] || target > A[right])
					right = mid - 1;
				else
					left = mid + 1;
			} else {
				// 3,4,5,(6),7,0,1,2
				if (target > A[mid] || target < A[left])
					left = mid + 1;
				else
					right = mid - 1;
			}
		}
		return -1;
	}
}
