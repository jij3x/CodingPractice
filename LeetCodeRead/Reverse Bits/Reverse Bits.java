public class Solution {
    private int swapBits(int x, int i, int j) {
        if (((x >> i) & 1) != ((x >> j) & 1))
            return x ^ ((1 << i) | (1 << j));
        return x;
    }

    public int reverseBits(int x) {
        for (int i = 1, j = 32; i < j; i++, j--) {
            x = swapBits(x, i - 1, j - 1);
        }
        return x;
    }

    public int reverseBits2(int x) {
        x = ((x & 0x55555555) << 1) | ((x & 0xAAAAAAAA) >> 1);
        x = ((x & 0x33333333) << 2) | ((x & 0xCCCCCCCC) >> 2);
        x = ((x & 0x0F0F0F0F) << 4) | ((x & 0xF0F0F0F0) >> 4);
        x = ((x & 0x00FF00FF) << 8) | ((x & 0xFF00FF00) >> 8);
        x = ((x & 0x0000FFFF) << 16) | ((x & 0xFFFF0000) >> 16);
        return x;
    }
}