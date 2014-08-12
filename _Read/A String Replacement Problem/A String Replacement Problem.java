public class Solution {
    private boolean isMatch(StringBuilder s, int ts, String p) {
        for (int i = 0; i < p.length(); i++) {
            if (ts + i >= s.length() || s.charAt(ts + i) != p.charAt(i))
                return false;
        }
        return true;
    }

    public String replace(String s, String p) {
        if (s.length() == 0 || p.length() == 0)
            return "";

        StringBuilder buffer = new StringBuilder(s);
        int len = 0;
        for (int pioneer = 0; pioneer < buffer.length(); pioneer++) {
            boolean matched = false;
            while (isMatch(buffer, pioneer, p)) {
                pioneer += p.length();
                matched = true;
            }

            if (matched)
                buffer.setCharAt(len++, 'X');

            if (pioneer != buffer.length())
                buffer.setCharAt(len++, buffer.charAt(pioneer));
        }
        buffer.setLength(len);
        return buffer.toString();
    }
}