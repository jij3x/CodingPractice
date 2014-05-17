public class Solution {
    public int maxArea(int[] height) {
        int start = 0, end = height.length - 1, max = 0;
        while (end > start) {
            max = Math.max(max, (end - start) * Math.min(height[start], height[end]));
            if (height[start] < height[end])
                start++;
            else
                end--;
        }
        return max;
    }
}
