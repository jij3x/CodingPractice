public class Solution {
	public int jump(int[] A) {
		int last = 0, max = 0, hops = 0;
		for (int i = 0; i < A.length; i++) {
			if (i > last) {
				last = max;
				hops++;
			}
			max = Math.max(max, i + A[i]);
		}

		return hops;
	}
}

class Solution2 {
	public int jump(int[] A) {
		int hops = 0;
		for (int next = 0, curr = 0, max = 0; next < A.length - 1; hops++) {
			for (; curr <= next; curr++)
				max = Math.max(max, curr + A[curr]);
			next = max;
		}

		return hops;
	}
}