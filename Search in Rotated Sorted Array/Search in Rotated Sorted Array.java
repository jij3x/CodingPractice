public class Solution {
	public int search(int[] A, int target) {
		int begin = 0;
		int end = A.length - 1;
		while (A[begin] > A[end]) {
			int middle = (begin + end) / 2;
			if (A[middle] < A[end])
				end = middle;
			else
                begin = middle + 1;
		}

        if (begin != 0) {
            if (target > A[A.length-1]) {
                end = begin -1;
                begin = 0;
            } else {
                end = A.length -1;
            }
        }

		while (begin <= end) {
			int middle = (begin + end) / 2;
			if (A[middle] == target)
				return middle;
			if (target > A[middle])
				begin = middle + 1;
			else
				end = middle - 1;
		}
		return -1;
	}
}
