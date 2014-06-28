public class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1, length = 0;
        while (i >= 0 && s.charAt(i) == ' ')
            i--;

        while (i >= 0) {
            if (s.charAt(i--) != ' ')
                length++;
            else
                break;
        }
        return length;
    }
}
