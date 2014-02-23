public class Solution {
	public int[] searchRange(int[] A, int target) {
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;

		int start = 0, end = A.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (A[mid] == target) {
				if (mid == 0 || A[mid - 1] < target) {
					result[0] = mid;
					break;
				} else {
					end = mid - 1;
				}
			} else if (A[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		start = 0;
		end = A.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (A[mid] == target) {
				if (mid + 1 == A.length || A[mid + 1] > target) {
					result[1] = mid;
					break;
				} else {
					start = mid + 1;
				}
			} else if (A[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return result;
	}
}
