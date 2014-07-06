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
        if (s.length() == 0)
            return 0;

        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        int start = 0, end = 0, max = 0;
        while (end < s.length()) {
            if (!charMap.containsKey(s.charAt(end))) {
                max = Math.max(max, end - start + 1);
                charMap.put(s.charAt(end), end);
                end++;
            } else {
                start = charMap.get(s.charAt(end)) + 1;
                end = start;
                charMap.clear();
            }
        }
        return max;
    }
}
