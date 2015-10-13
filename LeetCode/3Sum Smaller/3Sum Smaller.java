public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1, k = nums.length - 1; j < k;) {
                if (nums[i] + nums[j] + nums[k] < target) {
                    sum += k - j;
                    j++;
                } else {
                    k--;
                }
            }
        }
        return sum;
    }
}