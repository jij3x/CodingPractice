public class Solution {
    private String[] ones = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        String result = "";
        String billion = convert3(num / 1000000000);
        result += billion.isEmpty() ? "" : billion + " Billion";
        String million = convert3((num / 1000000) % 1000);
        result += million.isEmpty() ? "" : million + " Million";
        String thousand = convert3((num / 1000) % 1000);
        result += thousand.isEmpty() ? "" : thousand + " Thousand";
        result += convert3(num % 1000);
        return result.charAt(0) == ' ' ? result.substring(1) : result;
    }

    private String convert3(int num) {
        String result = "";
        if (num / 100 != 0)
            result += " " + ones[num / 100] + " Hundred";
        if (num % 100 >= 20)
            result += " " + tens[(num % 100) / 10] + (num % 10 == 0 ? "" : " " + ones[num % 10]);
        else if (num % 100 != 0)
            result += " " + ones[num % 100];
        return result;
    }
}