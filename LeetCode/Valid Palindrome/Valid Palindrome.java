public class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        for (int start = 0, end = s.length() - 1; start < end;) {
            if (!Character.isLetterOrDigit(s.charAt(start)))
                start++;
            else if (!Character.isLetterOrDigit(s.charAt(end)))
                end--;
            else if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }
}
