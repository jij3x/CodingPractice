public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        HashSet<Character> charSet = new HashSet<Character>();
        for (int end = 0, start = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (!charSet.contains(c)) {
                charSet.add(c);
                max = Math.max(max, end - start + 1);
            } else {
                while (start <= end) {
                    if (s.charAt(start++) == c)
                        break;
                    else
                        charSet.remove(s.charAt(start - 1));
                }
            }
        }
        return max;
    }
}

class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        int charMap[] = new int[256];
        int max = 0;
        for (int start = 0, end = 0, dup = 0; end < s.length();) {
            if (charMap[s.charAt(end++)]++ == 1) {
                dup++;
                while (dup > 0) {
                    if (--charMap[s.charAt(start++)] == 1)
                        dup--;
                }
            }
            max = Math.max(max, end - start);
        }
        return max;
    }
}
