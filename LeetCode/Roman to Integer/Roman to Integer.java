public class Solution {
    public int romanToInt(String s) {
        int[] dict = new int[128];
        dict['I'] = 1;
        dict['V'] = 5;
        dict['X'] = 10;
        dict['L'] = 50;
        dict['C'] = 100;
        dict['D'] = 500;
        dict['M'] = 1000;

        int result = dict[s.charAt(s.length() - 1)];
        for (int i = s.length() - 2; i >= 0; i--) {
            result += dict[s.charAt(i)] >= dict[s.charAt(i + 1)] ? dict[s.charAt(i)] : -dict[s.charAt(i)];
        }
        return result;
    }
}
