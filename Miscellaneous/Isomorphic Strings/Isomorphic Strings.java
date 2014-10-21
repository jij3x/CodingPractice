public class Solution {
    public boolean isIsomophic(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        int[] map1 = new int[256];
        int[] map2 = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            map1[s1.charAt(i)] = i;
            map2[s2.charAt(i)] = i;
        }

        int[] enS1 = new int[s1.length()];
        int[] enS2 = new int[s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            enS1[i] = map1[s1.charAt(i)];
            enS2[i] = map2[s2.charAt(i)];
        }

        for (int i = 0; i < s1.length(); i++) {
            if (enS1[i] != enS2[i])
                return false;
        }
        return true;
    }
}

class Solution2 {
    public boolean isIsomophic(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        char[] charMap = new char[256];
        char[] revCharMap = new char[256];
        for (int i = 0; i < s1.length(); i++) {
            if (charMap[s1.charAt(i)] == 0 && revCharMap[s2.charAt(i)] == 0) {
                charMap[s1.charAt(i)] = s2.charAt(i);
                revCharMap[s2.charAt(i)] = s1.charAt(i);
            } else if (charMap[s1.charAt(i)] == 0 || revCharMap[s2.charAt(i)] == 0
                    || charMap[s1.charAt(i)] != s2.charAt(i) || revCharMap[s2.charAt(i)] != s1.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
