public class Solution {
	public int[] twoSum(int[] numbers, int target) {
		int[] result = new int[2];
		HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			if (table.containsKey(target - numbers[i])) {
				result[0] = table.get(target - numbers[i]) + 1;
				result[1] = i + 1;
			} else {
				table.put(numbers[i], i);
			}
		}
		return result;
	}
}