public class Solution {
	public String intToRoman(int num) {
		String[] thousands = { "", "M", "MM", "MMM" };
		String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

		return thousands[num / 1000] + hundreds[(num / 100) % 10] + tens[(num / 10) % 10] + ones[num % 10];
	}
}

class Solution2 {
	public String intToRoman(int num) {
		String[] roman = { "M", "DM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] number = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

		int pivot = 0;
		String result = "";
		while (num > 0) {
			int c = num / number[pivot];
			num -= c * number[pivot];
			for (int i = 1; i <= c; i++)
				result += roman[pivot];
			pivot++;
		}
		return result;
	}
}