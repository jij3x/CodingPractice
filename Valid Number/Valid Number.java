public class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if (isInteger(s) || isFloat(s) || isENotion(s))
            return true;

        return false;
    }

    private boolean isInteger(String s) {
        if (!s.isEmpty() && (s.charAt(0) == '+' || s.charAt(0) == '-'))
            s = s.substring(1);

        return !s.isEmpty() && allDigits(s);
    }

    private boolean isFloat(String s) {
        if (!s.isEmpty() && (s.charAt(0) == '+' || s.charAt(0) == '-'))
            s = s.substring(1);

        int dotIdx = 0;
        for (; dotIdx < s.length(); dotIdx++) {
            if (s.charAt(dotIdx) == '.')
                break;
        }
        if (dotIdx == s.length())
            return false;

        return s.length() > 1 && allDigits(s.substring(0, dotIdx)) && allDigits(s.substring(dotIdx + 1));
    }

    private boolean isENotion(String s) {
        int expIdx = 0;
        for (; expIdx < s.length(); expIdx++) {
            if (s.charAt(expIdx) == 'E' || s.charAt(expIdx) == 'e')
                break;
        }
        if (expIdx == s.length())
            return false;

        return (isInteger(s.substring(0, expIdx)) || isFloat(s.substring(0, expIdx)))
                && isInteger(s.substring(expIdx + 1));
    }

    private boolean allDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9')
                return false;
        }
        return true;
    }
}

/*
 * DFA
 */
class Solution2 {
    public boolean isNumber(String s) {
        s = s.trim();

        /*
         *     | +/- | 0-9 | e/E | .
         *  ---+-----+-----+-----+----
         *   0 |  1  |  2  | -1  |  3
         *   1 | -1  |  2  | -1  |  3
         *   2 | -1  |  2  |  5  |  4
         *   3 | -1  |  4  | -1  | -1
         *   4 | -1  |  4  |  5  | -1
         *   5 |  6  |  7  | -1  | -1
         *   6 | -1  |  7  | -1  | -1
         *   7 | -1  |  7  | -1  | -1
         */
        int[][] delta = { { 1, 2, -1, 3 }, { -1, 2, -1, 3 }, { -1, 2, 5, 4 }, { -1, 4, -1, -1 }, { -1, 4, 5, -1 },
                { 6, 7, -1, -1 }, { -1, 7, -1, -1 }, { -1, 7, -1, -1 } };

        int state = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                state = delta[state][0];
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                state = delta[state][1];
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                state = delta[state][2];
            } else if (s.charAt(i) == '.') {
                state = delta[state][3];
            } else {
                return false;
            }

            if (state == -1)
                return false;
        }

        if (state == 2 || state == 4 || state == 7)
            return true;

        return false;
    }
}

class Solution3 {
    public boolean isNumber(String s) {
        return s.matches("^\\s*[+-]?(\\d+|\\d*\\.\\d+|\\d+\\.\\d*)([eE][+-]?\\d+)?\\s*$");
    }
}
