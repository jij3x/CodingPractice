public class Solution {
    public int[] plusOne(int[] digits) {
        int[] buf = new int[digits.length];
        int carry = 1;

        for (int i = digits.length - 1; i >= 0; i--) {
            buf[i] += digits[i] + carry;
            carry = buf[i] / 10;
            buf[i] %= 10;
        }

        if (carry == 0)
            return buf;
        
        int[] buf2 = new int[buf.length + 1];
        buf2[0] = carry;
        System.arraycopy(buf, 0, buf2, 1, buf.length);
        return buf2;
    }
}
