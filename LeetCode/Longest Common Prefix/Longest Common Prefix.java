public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs[0].isEmpty())
            return "";

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != strs[0].charAt(i))
                    return result.toString();
            }
            result.append(strs[0].charAt(i));
        }
        return result.toString();
    }
}
