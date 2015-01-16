public class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int start = 0, end = height.length - 1; start < end;) {
            max = Math.max(max, Math.min(height[start], height[end]) * (end - start));
            if (height[start] < height[end])
                start++;
            else
                end--;
        }
        return max;
    }
}
