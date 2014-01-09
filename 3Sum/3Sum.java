public class Solution {
	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		Arrays.sort(num);
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= num.length - 3; i++) {
			int left = i + 1, right = num.length - 1;
			while (left < right) {
				if (num[left] + num[right] == -num[i]) {
					ArrayList<Integer> subResult = new ArrayList<Integer>();
					subResult.add(num[i]);
					subResult.add(num[left++]);
					subResult.add(num[right--]);
					result.add(subResult);

					while (left < right && num[left] == num[left - 1])
						left++;
					while (left < right && num[right] == num[right + 1])
						right--;
				} else if (num[left] + num[right] > -num[i]) {
					right--;
				} else {
					left++;
				}
			}

			while (i < num.length - 1 && num[i] == num[i + 1])
				i++;
		}
		return result;
	}
}