public class Solution {
    public String reverseWords(String s) {
        StringBuilder word = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length();) {
            while (i < s.length() && s.charAt(i) == ' ')
                i++;

            while (i < s.length() && s.charAt(i) != ' ')
                word.append(s.charAt(i++));

            if (word.length() > 0) {
                result.insert(0, word.append(result.length() == 0 ? "" : " "));
                word.setLength(0);
            }
        }
        return result.toString();
    }
}