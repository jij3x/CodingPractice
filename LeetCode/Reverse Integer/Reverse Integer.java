public class Solution {
    public int reverse(int x) {
        int result = 0;
        for (int r = 0; x != 0; x /= 10, result = r) {
            if ((r = r * 10 + x % 10) / 10 != result)
                return 0;
        }
        return result;
    }
}
