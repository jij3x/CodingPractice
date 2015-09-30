public class Solution {
    public int hIndex(int[] citations) {
        int left = 0, right = citations.legnth - 1, mid;
        while (left <= right) {
            int h = citations.length - (mid = left + (right - left) / 2);
            if (citations[mid] >= h && (mid == 0 || citations[mid - 1] < h + 1))
                return h;
            else if (citations[mid] < h)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return 0;
    }
}