public class Solution {
	public int candy(int[] ratings) {
		int[] count = new int[ratings.length];
		count[0] = 1;

		for (int i = 1; i < ratings.length; i++) {
			count[i] = ratings[i] > ratings[i - 1] ? count[i - 1] + 1 : 1;
		}
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1] && count[i] <= count[i + 1])
				count[i] = count[i + 1] + 1;
		}

		int sum = 0;
		for (int c : count)
			sum += c;
		return sum;
	}
}