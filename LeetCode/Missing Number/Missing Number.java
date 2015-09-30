public class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        
        long expect = (1 + nums.length) * nums.length / 2;
        return (int) (expect - sum);
    }
}