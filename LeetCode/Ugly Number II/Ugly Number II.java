public class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n + 1];
        ugly[1] = 1;
        for (int f2Idx = 1, f3Idx = 1, f5Idx = 1, i = 1; i < n;) {
            ugly[++i] = Math.min(ugly[f2Idx] * 2, Math.min(ugly[f3Idx] * 3, ugly[f5Idx] * 5));
            f2Idx += ugly[f2Idx] * 2 <= ugly[i] ? 1 : 0;
            f3Idx += ugly[f3Idx] * 3 <= ugly[i] ? 1 : 0;
            f5Idx += ugly[f5Idx] * 5 <= ugly[i] ? 1 : 0;
        }
        return ugly[n];
    }
}