public class Solution {
    public int findMin(int[] num) {
        int start = 0, end = num.length - 1;
        while (num[start] > num[end]) {
            int mid = (start + end) / 2;
            if (num[start] > num[mid])
                end = mid;
            else
                start = mid + 1;
        }
        return num[start];
    }
}
