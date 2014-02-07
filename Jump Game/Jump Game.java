public class Solution {
	public boolean canJump(int[] A) {
		int battery = 1;
		for (int i = 0; i < A.length; i++) {
			if (--battery < 0)
				return false;
			battery = Math.max(battery, A[i]);
		}

		return true;
	}
}