public class Solution {
    public int romanToInt(String s) {
        int[] charMap = new int[256];
        charMap['I'] = 1;
        charMap['V'] = 5;
        charMap['X'] = 10;
        charMap['L'] = 50;
        charMap['C'] = 100;
        charMap['D'] = 500;
        charMap['M'] = 1000;

        int result = charMap[s.charAt(s.length() - 1)];
        for (int i = s.length() - 2; i >= 0; i--)
            result += (charMap[s.charAt(i)] < charMap[s.charAt(i + 1)] ? -1 : 1) * charMap[s.charAt(i)];

        return result;
    }
}
