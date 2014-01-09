public class Solution {
	public void sortColors(int[] A) {
		int redAfter = 0;
		int blueBefore = A.length - 1;
		int i = 0;
		while (i <= blueBefore) {
			if (A[i] == 0) {
				A[i] = A[redAfter];
				A[redAfter] = 0;
				redAfter++;
				i++;
			} else if (A[i] == 2) {
				A[i] = A[blueBefore];
				A[blueBefore] = 2;
				blueBefore--;
			} else {
				i++;
			}
		}
	}
}
