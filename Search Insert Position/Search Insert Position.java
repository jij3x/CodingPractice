public class Solution {
	public int searchInsert(int[] A, int target) {
		int head = 0, tail = A.length - 1;
		while (head <= tail) {
			int mid = head + (tail - head) / 2;
			if (A[mid] == target)
				return mid;
			else if (A[mid] < target)
				head = mid + 1;
			else
				tail = mid - 1;
		}
		return head;
	}
}