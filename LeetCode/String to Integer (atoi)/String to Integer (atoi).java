public class Solution {
    public int atoi(String str) {
        long result = 0;
        int sign = 1, idx = 0;
        while (idx < str.length() && str.charAt(idx) == ' ')
            idx++;
        if (idx < str.length() && (str.charAt(idx) == '+' || str.charAt(idx) == '-'))
            sign = str.charAt(idx++) == '+' ? 1 : -1;

        while (idx < str.length() && Character.isDigit(str.charAt(idx))) {
            int digit = str.charAt(idx++) - '0';

            result = result * 10 + digit;
            if (result * sign >= Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (result * sign <= Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }
        return (int) result * sign;
    }
}
