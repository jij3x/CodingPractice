public class Solution {
    public String reverseWords(String s) {
        StringBuilder word = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); word.setLength(0)) {
            while (i < s.length() && s.charAt(i) == ' ')
                i++;

            while (i < s.length() && s.charAt(i) != ' ')
                word.append(s.charAt(i++));

            if (word.length() > 0)
                result.insert(0, word.append(" "));
        }
        return result.substring(0, Math.max(0, result.length() - 1));
    }
}
