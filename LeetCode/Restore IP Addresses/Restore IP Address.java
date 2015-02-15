public class Solution {
    public List<String> restoreIpAddresses(String s) {
        return dfs(s, 0, 0);
    }

    private List<String> dfs(String s, int start, int level) {
        ArrayList<String> result = new ArrayList<String>();
        if (level == 4) {
            if (start == s.length())
                result.add("");
            return result;
        }

        for (int i = start; i < s.length() && i < start + 3; i++) {
            int segment = Integer.parseInt(s.substring(start, i + 1));
            if (segment > 255 || (i > start && s.charAt(start) == '0'))
                break;

            for (String row : dfs(s, i + 1, level + 1))
                result.add(s.substring(start, i + 1) + (row.isEmpty() ? "" : ".") + row);
        }
        return result;
    }
}

class Solution2 {
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i <= 3 && i < s.length(); i++) {
            String first = s.substring(0, i + 1);
            if (isInvalid(first))
                break;
            for (int j = i + 1; j <= i + 3 && j < s.length(); j++) {
                String second = s.substring(i + 1, j + 1);
                if (isInvalid(second))
                    break;
                for (int k = j + 1; k <= j + 3 && k < s.length(); k++) {
                    String third = s.substring(j + 1, k + 1);
                    if (isInvalid(third))
                        break;

                    if (k + 1 >= s.length())
                        break;
                    String fourth = s.substring(k + 1);
                    if (fourth.length() <= 3 && !isInvalid(fourth))
                        result.add(first + '.' + second + '.' + third + '.' + fourth);
                }
            }
        }

        return result;
    }

    private boolean isInvalid(String s) {
        return (s.length() > 1 && s.charAt(0) == '0') || Integer.parseInt(s) > 255;
    }
}
