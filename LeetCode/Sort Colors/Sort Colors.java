public class Solution {
    public void sortColors(int[] A) {
        int redAfter = 0, blueBefore = A.length - 1, whitePtr = 0;
        while (whitePtr <= blueBefore) {
            if (A[whitePtr] == 2) {
                A[whitePtr] = A[blueBefore];
                A[blueBefore--] = 2;
            } else if (A[whitePtr] == 0) {
                A[whitePtr++] = A[redAfter];
                A[redAfter++] = 0;
            } else {
                whitePtr++;
            }
        }
    }
}
