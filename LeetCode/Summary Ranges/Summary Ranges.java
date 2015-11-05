public class Solution {
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0)
            return new ArrayList<String>();

        List<String> result = new ArrayList<String>();
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1] + 1) {
                result.add(start == i - 1 ? Integer.toString(nums[start]) : Integer.toString(nums[start]) + "->"
                        + Integer.toString(nums[i - 1]));
                start = i;
            }
        }
        result.add(start == nums.length - 1 ? Integer.toString(nums[start]) : Integer.toString(nums[start]) + "->"
                + Integer.toString(nums[nums.length - 1]));
        return result;
    }
}