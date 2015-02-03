public class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1, length = 0;
        while (i >= 0 && s.charAt(i) == ' ')
            i--;

        for (; i >= 0; i--, length++) {
            if (s.charAt(i) == ' ')
                break;
        }
        return length;
    }
}
