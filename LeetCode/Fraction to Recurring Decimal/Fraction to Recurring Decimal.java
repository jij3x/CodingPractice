public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        String sign = (numerator >= 0 && denominator >= 0) || (numerator <= 0 && denominator <= 0) ? "" : "-";
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        StringBuilder result = new StringBuilder(Long.toString(num / den));
        if (num % den == 0)
            return sign + result;

        result.append(".");
        int p = result.length();
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        for (long n = num % den; n > 0; p++) {
            if (map.containsKey(n))
                return sign + result.substring(0, map.get(n)) + "(" + result.substring(map.get(n)) + ")";

            map.put(n, p);
            result.append(Long.toString((n *= 10) / den));
            n %= den;
        }
        return sign + result;
    }
}