public class Solution {
    public int romanToInt(String s) {
        int[] charMap = new int[256];
        charMap[(int) 'I'] = 1;
        charMap[(int) 'V'] = 5;
        charMap[(int) 'X'] = 10;
        charMap[(int) 'L'] = 50;
        charMap[(int) 'C'] = 100;
        charMap[(int) 'D'] = 500;
        charMap[(int) 'M'] = 1000;

        int result = charMap[s.charAt(s.length() - 1)];
        for (int i = s.length() - 2; i >= 0; i--)
            result += (charMap[s.charAt(i)] < charMap[s.charAt(i + 1)] ? -1 : 1) * charMap[s.charAt(i)];

        return result;
    }
}
