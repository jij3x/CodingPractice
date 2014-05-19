public class Solution {
    public String strStr(String haystack, String needle) {
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean found = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found)
                return haystack.substring(i);
        }
        return null;
    }
}

class Solution2 {
    public String strStr(String haystack, String needle) {
        if (haystack.isEmpty() && needle.isEmpty())
            return "";
        else if (haystack.isEmpty())
            return null;
        else if (needle.isEmpty())
            return haystack;

        int m = 0, i = 0;
        int[] kmpTbl = new int[needle.length() + 2];
        preCompute(needle, kmpTbl);

        while (m + i < haystack.length()) {
            if (haystack.charAt(m + i) == needle.charAt(i)) {
                if (i == needle.length() - 1)
                    return haystack.substring(m);
                i++;
            } else {
                m = m + i - kmpTbl[i];
                if (kmpTbl[i] == -1)
                    i = 0;
                else
                    i = kmpTbl[i];
            }
        }
        return null;
    }

    private void preCompute(String p, int[] kmpTbl) {
        kmpTbl[0] = -1;
        kmpTbl[1] = 0;

        int cnd = 0, pos = 2;
        while (pos < p.length()) {
            if (p.charAt(pos - 1) == p.charAt(cnd)) {
                cnd++;
                kmpTbl[pos] = cnd;
                pos++;
            } else if (cnd > 0) {
                cnd = kmpTbl[cnd];
            } else {
                kmpTbl[pos] = 0;
                pos++;
            }
        }
    }
}
