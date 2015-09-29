public class Solution {
    int findDuplicate(int[] nums) {
        int slow = nums.length, fast = slow;
        do {
            slow = nums[slow - 1];
            fast = nums[nums[fast - 1] - 1];
        } while (slow != fast);

        for (fast = nums.length; slow != fast;) {
            slow = nums[slow - 1];
            fast = nums[fast - 1];
        }
        return slow;
    }
}