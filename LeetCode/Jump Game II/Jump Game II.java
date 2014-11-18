public class Solution {
    public int jump(int[] A) {
        int hops = 0;
        for (int i = 0, currRange = 0, nextRange = 0; i < A.length; i++) {
            if (i > currRange) {
                currRange = nextRange;
                hops++;
            }
            nextRange = Math.max(nextRange, i + A[i]);
        }
        return hops;
    }
}

class Solution2 {
    public int jump(int[] A) {
        int hops = 0;
        for (int i = 0, currRange = 0, nextRange = 0; currRange < A.length - 1; hops++) {
            for (; i <= currRange; i++)
                nextRange = Math.max(nextRange, i + A[i]);
            currRange = nextRange;
        }
        return hops;
    }
}
