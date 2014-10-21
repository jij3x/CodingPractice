public class Solution {
    private String[] thousands = { "", "M", "MM", "MMM" };
    private String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
    private String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
    private String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

    public String intToRoman(int num) {
        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }
}

class Solution2 {
    public String intToRoman(int num) {
        String[] roman = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] number = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        String result = "";
        for (int i = 0; num > 0; i++) {
            int cnt = num / number[i];
            num -= cnt * number[i];
            for (int j = 1; j <= cnt; j++)
                result += roman[i];
        }
        return result;
    }
}
