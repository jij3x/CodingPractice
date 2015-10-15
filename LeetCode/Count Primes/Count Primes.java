public class Solution {
    public int countPrimes(int n) {
        boolean[] tbl = new boolean[n + 1];
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            cnt += tbl[i] ? 0 : 1;
            for (int j = i; j < n; j += i)
                tbl[j] = true;
        }
        return cnt;
    }
}