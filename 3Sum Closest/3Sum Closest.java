public class Solution {
	public int threeSumClosest(int[] num, int target) {
		int result = num[0] + num[1] + num[2];
		Arrays.sort(num);

		for (int i = 0; i <= num.length - 3; i++) {
			int left = i + 1, right = num.length - 1;
			while (left < right) {
				int temp = num[i] + num[left] + num[right];
				if (Math.abs(temp - target) < Math.abs(result - target))
					result = temp;

				if (temp == target)
					return target;
				else if (temp < target)
					left++;
				else
					right--;
			}
		}
		return result;
	}
}
