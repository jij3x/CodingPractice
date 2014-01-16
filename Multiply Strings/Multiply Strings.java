public class Solution {
	public String multiply(String num1, String num2) {
		int[] result = new int[num1.length() + num2.length()];

		for (int i = num1.length() - 1; i >= 0; i--) {
			int carry = 0;
			for (int j = num2.length() - 1; j >= 0; j--) {
				int product = ((int) num2.charAt(j) - (int) '0') * ((int) num1.charAt(i) - (int) '0');
				product += carry + result[i + j + 1];
				result[i + j + 1] = product % 10;
				carry = product / 10;
			}
			result[result.length - num2.length() - 1 - (num1.length() - i - 1)] = carry;
		}

		int start = 0;
		while (start < result.length - 1 && result[start] == 0)
			start++;

		String ret = "";
		for (int i = start; i < result.length; i++)
			ret += (char) (result[i] + (int) '0');
		return ret;
	}
}