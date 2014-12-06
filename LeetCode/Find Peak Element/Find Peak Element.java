public class Solution {
    public int findPeakElement(int[] num) {
        int start = 0, end = num.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (num[mid] > num[mid + 1])
                end = mid;
            else
                start = mid + 1;
        }
        return start;
    }
}