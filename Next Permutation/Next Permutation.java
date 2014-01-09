public class Solution {
	public void nextPermutation(int[] num) {
		int pivot = num.length - 2;
		while (pivot >= 0) {
			if (num[pivot] < num[pivot + 1]) {
				int i = pivot + 1;
				while (i < num.length) {
					if (num[i] <= num[pivot])
						break;
					i++;
				}
				int temp = num[pivot];
				num[pivot] = num[i - 1];
				num[i - 1] = temp;
				break;
			}
			pivot--;
		}
		reverse(num, pivot + 1);
	}

	private void reverse(int[] num, int start) {
		int n = num.length - 1;
		for (int i = start; i <= start + (n - start) / 2; i++) {
			int temp = num[i];
			num[i] = num[n - (i - start)];
			num[n - (i - start)] = temp;
		}
		return;
	}
}
