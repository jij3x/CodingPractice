public class Solution {
    public int titleToNumber(String s) {
        int r = 0;
        for (int i = 0; i < s.length(); i++)
            r = r * 26 + (s.charAt(i) - 'A' + 1);
        return r;
    }
}