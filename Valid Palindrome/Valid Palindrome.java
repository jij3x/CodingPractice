public class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        for (int start = 0, end = s.length() - 1; start < end;) {
            if (!Character.isLetter(s.charAt(start)) && !Character.isDigit(s.charAt(start)))
                start++;
            else if (end >= 0 && !Character.isLetter(s.charAt(end)) && !Character.isDigit(s.charAt(end)))
                end--;
            else if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }
}
