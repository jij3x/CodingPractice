public class Solution {
    public boolean isMatch(String s, String p) {
        return match(s, 0, p, 0);
    }

    private boolean match(String s, int si, String p, int pi) {
        if (pi == p.length())
            return si == s.length();

        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
            return (si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') && match(s, si + 1, p, pi))
                    || match(s, si, p, pi + 2);
        }

        return si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') && match(s, si + 1, p, pi + 1);
    }
}
