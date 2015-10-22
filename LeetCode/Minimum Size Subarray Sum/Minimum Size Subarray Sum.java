public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int min = nums.length + 1;
        for (int start = 0, end = 0, sum = 0; end < nums.length; end++) {
            if ((sum += nums[end]) >= s) {
                min = Math.min(min, end - start + 1);
                while (sum >= s)
                    sum -= nums[start++];
                min = Math.min(min, end - start + 2);
            }
        }
        return min == nums.length + 1 ? 0 : min;
    }
}