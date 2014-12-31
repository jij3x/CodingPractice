public class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        for (int i = 5; i <= n; i *= 5) {
            count += n / i;
        }
        return count;
    }
}

class Solution2 {
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
