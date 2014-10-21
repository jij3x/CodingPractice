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
