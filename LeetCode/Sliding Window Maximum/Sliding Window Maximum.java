public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new int[0];

        int[] result = new int[nums.length + 1 - k];
        Deque<Integer> dq = new LinkedList<Integer>();
        for (int i = 0, j = 1 - k; i < nums.length; i++, j++) {
            if (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.removeFirst();
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.removeLast();
            dq.addLast(i);
            if (j >= 0)
                result[j] = nums[dq.peekFirst()];
        }
        return result;
    }
}