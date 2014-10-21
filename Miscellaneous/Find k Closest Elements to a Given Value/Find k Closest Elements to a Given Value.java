public class Solution {
    public List<Integer> kClosest(int[] A, int x, int k) {
        assert (k <= A.length);
        ArrayList<Integer> result = new ArrayList<Integer>();

        int left = 0, right = A.length - 1, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            if ((mid == 0 && A[mid] >= x) || (mid > 0 && A[mid - 1] < x && A[mid] >= x))
                break;
            else if (A[mid] >= x)
                right = mid - 1;
            else
                left = mid + 1;
        }

        left = mid - 1;
        right = mid;
        while (k-- > 0) {
            if (left >= 0 && right < A.length) {
                int deltaLeft = Math.abs(A[left] - x);
                int deltaRight = Math.abs(A[right] - x);

                if (deltaLeft < deltaRight) {
                    result.add(A[left--]);
                } else if (deltaLeft > deltaRight) {
                    result.add(A[right++]);
                } else {
                    result.add(A[left--]);
                    result.add(A[right++]);
                }
            } else if (left < 0) {
                result.add(A[right++]);
            } else {
                result.add(A[left--]);
            }
        }
        return result;
    }
}