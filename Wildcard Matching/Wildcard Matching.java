public class Solution {
    public boolean isMatch(String s, String p) {
        int si = 0, pi = 0, pp = 0, sp = 0;
        boolean seenStar = false;
        while (si < s.length()) {
            if (pi == p.length()) {
                if (!seenStar)
                    return false;

                // wildcard was detected. s:"ABCDEF"; p:"AB*CD";
                seenStar = false; // to match last pattern in p ("CD"), and, s' tail ("EF")
                si = s.length() - (p.length() - pp);
                pi = pp;
            } else if (p.charAt(pi) == '*') {
                seenStar = true;
                pp = ++pi;
                sp = si;
            } else if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?') {
                pi++;
                si++;
            } else if (seenStar) {
                si = ++sp;
                pi = pp;
            } else {
                return false;
            }
        }

        while (pi < p.length()) { // if match, all remain chars in p should be '*'
            if (p.charAt(pi++) != '*')
                return false;
        }

        return true;
    }
}

class Solution2 {
    /*
     *    | a | b | c | d | e | f |   
     * ---+---+---+---+---+---+---+---
     *  a | T | F | F | F | F | F | F 
     * ---+---+---+---+---+---+---+---
     *  * | T | T | T | F | F | F | F 
     * ---+---+---+---+---+---+---+---
     *  c | F | F | T | F | F | F | F 
     * ---+---+---+---+---+---+---+---
     *  * | T | T | T | T | T | T | F 
     * ---+---+---+---+---+---+---+---
     *  f | F | F | F | F | F | T | F 
     * ---+---+---+---+---+---+---+---
     *    | F | F | F | F | F | F | T 
     */
    public boolean isMatch(String s, String p) {
        // character counts in pattern should not exceed total characters in s
        for (int i = 0, c = 0; i < p.length(); i++) {
            if (p.charAt(i) != '*' && ++c > s.length())
                return false;
        }

        boolean[] memo = new boolean[s.length() + 1];
        memo[memo.length - 1] = true;

        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) == '*') {
                boolean matched = false;
                for (int j = s.length(); j >= 0; j--) {
                    memo[j] = memo[j] || matched;
                    matched = memo[j];
                }
            } else {
                for (int j = 0; j < s.length(); j++)
                    memo[j] = (p.charAt(i) == s.charAt(j) || p.charAt(i) == '?') ? memo[j + 1] : false;
                memo[memo.length - 1] = false; // s:'', p:'?*'
            }
        }
        return memo[0];
    }
}

class Solution3 {
    public boolean isMatch(String s, String p) {
        byte[][] memo = new byte[s.length() + 1][p.length() + 1];
        memo[s.length()][p.length()] = 1;
        for (int i = 0; i < s.length(); i++)
            memo[i][p.length()] = -1;

        return dpIsMatch(s, 0, p, 0, memo);
    }

    private boolean dpIsMatch(String s, int ss, String p, int ps, byte[][] memo) {
        if (memo[ss][ps] != 0)
            return memo[ss][ps] == 1 ? true : false;

        if (p.charAt(ps) != '*') {
            if ((ss < s.length()) && (s.charAt(ss) == p.charAt(ps) || p.charAt(ps) == '?')
                    && dpIsMatch(s, ss + 1, p, ps + 1, memo)) {
                memo[ss][ps] = 1;
                return true;
            } else {
                memo[ss][ps] = -1;
                return false;
            }
        }

        for (int i = ss; i <= s.length(); i++) {
            if (dpIsMatch(s, i, p, ps + 1, memo)) {
                memo[ss][ps] = 1;
                return true;
            }
        }

        memo[ss][ps] = -1;
        return false;
    }
}

/*
 * Just for idea of matching process. With only basic backtracking mechanism, very inefficient.
 */
class Solution4 {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty() && s.isEmpty())
            return true;
        else if (p.isEmpty())
            return false;

        if (p.charAt(0) != '*')
            return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?')
                    && isMatch(s.substring(1), p.substring(1));

        for (int i = 0; i <= s.length(); i++) {
            if (isMatch(s.substring(i), p.substring(1)))
                return true;
        }

        return false;
    }
}
