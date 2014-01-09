public class Solution {
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		Arrays.sort(num);

		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= num.length - 4; i++) {
			if (i != 0 && num[i] == num[i - 1])
				continue;

			for (int j = i + 1; j <= num.length - 3; j++) {
				if (j != i + 1 && num[j] == num[j - 1])
					continue;

				int head = j + 1;
				int end = num.length - 1;
				while (head < end) {
					if (num[i] + num[j] + num[head] + num[end] == target) {
						ArrayList<Integer> subResult = new ArrayList<Integer>();
						subResult.add(num[i]);
						subResult.add(num[j]);
						subResult.add(num[head++]);
						subResult.add(num[end--]);
						result.add(subResult);

						while (head < end && num[head] == num[head - 1])
							head++;

						while (head < end && num[end] == num[end + 1])
							end--;
					} else if (num[i] + num[j] + num[head] + num[end] < target) {
						head++;
					} else {
						end--;
					}
				}
			}
		}
		return result;
	}
}