public class Solution {
    public int findMin(int[] num) {
        int start = 0, end = num.length - 1;
        while (num[start] >= num[end] && start < end) {
            if (num[start] == num[end]) {
                end--;
            } else {
                int mid = (start + end) / 2;
                if (num[mid] > num[end])
                    start = mid + 1;
                else
                    end = mid;
            }
        }
        return num[start];
    }
}