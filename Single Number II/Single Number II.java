public class Solution {
    public int singleNumber(int[] A) {
        int highBits = 0, lowBits = 0;
        for (int i = 0, flip = 0, carry = 0; i < A.length; i++) {
            flip = ~(highBits & A[i]);
            carry = lowBits & A[i];

            lowBits = (lowBits ^ A[i]) & flip;
            highBits = (highBits ^ carry) & flip;
        }
        return highBits | lowBits;
    }
}

class Solution2 {
    public int singleNumber(int[] A) {
        int[] digits = new int[32];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                digits[j] += (A[i] >> j) & 1;
            }
        }

        int result = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            result <<= 1;
            result |= digits[i] % 3 == 0 ? 0 : 1;
        }
        return result;
    }
}
