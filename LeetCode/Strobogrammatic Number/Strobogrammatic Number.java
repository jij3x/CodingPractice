public class Solution {
    public boolean isStrobogrammatic(String num) {
        for (int i = 0, j = num.length() - 1; i <= num.length() / 2; i++, j--) {
            char ci = num.charAt(i), cj = num.charAt(j);
            if (!((ci == cj && (ci == '8' || ci == '1' || ci == '0')) || ((ci == '6' && cj == '9') || (ci == '9' && cj == '6'))))
                return false;
        }
        return true;
    }
}