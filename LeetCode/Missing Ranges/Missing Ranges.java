public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        if (nums.length == 0) {
            result.add(rangeString(lower, upper));
            return result;
        }

        if (nums[0] > lower)
            result.add(rangeString(lower, nums[0] - 1));

        for (int prev = nums[0], i = 0; i < nums.length; prev = nums[i++]) {
            if (nums[i] > prev + 1)
                result.add(rangeString(prev + 1, nums[i] - 1));
        }

        if (nums[nums.length - 1] < upper)
            result.add(rangeString(nums[nums.length - 1] + 1, upper));

        return result;
    }

    private String rangeString(int lower, int upper) {
        return lower == upper ? Integer.toString(lower) : Integer.toString(lower) + "->" + Integer.toString(upper);
    }
}