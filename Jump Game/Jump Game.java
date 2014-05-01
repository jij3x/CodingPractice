public class Solution {
    public boolean canJump(int[] A) {
        for (int battery = 0, i = 0; i < A.length - 1; i++, battery--) {
            battery = Math.max(battery, A[i]);
            if (battery == 0)
                return false;
        }
        return true;
    }
}
