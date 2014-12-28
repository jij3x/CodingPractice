public class Solution {
    public int[] plusOne(int[] digits) {
        int[] result = new int[digits.length];
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            result[i] = digits[i] + carry;
            carry = result[i] / 10;
            result[i] %= 10;
        }

        if (carry == 0)
            return result;

        int[] result2 = new int[digits.length + 1];
        result2[0] = carry;
        System.arraycopy(result, 0, result2, 1, result.length);
        return result2;
    }
}
