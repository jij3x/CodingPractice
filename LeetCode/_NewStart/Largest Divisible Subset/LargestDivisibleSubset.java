public class Solution {
  public List<Integer> largestDivisibleSubset(int[] nums) {
    List<Integer> result = new LinkedList<>();
    if (nums.length == 0) {
      return result;
    }
    Arrays.sort(nums);

    int maxIdx = 0, maxVal = 0;
    int[] memo = new int[nums.length], parent = new int[nums.length];
    Arrays.fill(parent, -1);
    for (int right = 1; right < memo.length; right++) {
      for (int left = right - 1; left >= 0; left--) {
        if (nums[right] % nums[left] == 0 && memo[left] + 1 > memo[right]) {
          memo[right] = memo[left] + 1;
          parent[right] = left;
          if (memo[right] > maxVal) {
            maxVal = memo[right];
            maxIdx = right;
          }
        }
      }
    }

    for (int i = maxIdx; i >= 0;) {
      result.add(0, nums[i]);
      i = parent[i];
    }

    return result;
  }
}
