public class Solution {
	public String getPermutation(int n, int k) {
		int[] digits = new int[n];
		for (int i = 0; i < n; i++) {
			digits[i] = i + 1;
		}

		String result = "";
		while (n >= 1) {
			int idx = (k - 1) / factorial(n - 1);
			int num = digits[idx];
			for (int i = idx; i < n - 1; i++) {
				digits[i] = digits[i + 1];
			}

			result += Integer.toString(num);
			k = (k - 1) % factorial(n - 1) + 1;
			n--;
		}
		return result;
	}

	private int factorial(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}

		return result;
	}
}

class Solution2 {
	public String getPermutation(int n, int k) {
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = i + 1;
		}
		return subGetPermutation(num, n, k);
	}

	private String subGetPermutation(int[] num, int length, int k) {
		String result = "";
		if (k == 1) {
			for (int i = 0; i < length; i++)
				result += Integer.toString(num[i]);
			return result;
		}

		int index = (k - 1) / factorial(length - 1);
		result = Integer.toString(num[index]);
		for (int i = index; i < num.length - 1; i++) {
			num[i] = num[i + 1];
		}

		result += subGetPermutation(num, length - 1, (k - 1) % factorial(length - 1) + 1);
		return result;
	}

	private int factorial(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}

		return result;
	}
}