public class Solution {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        byte[] memo = new byte[s.length() + 1];
        memo[s.length()] = 1;

        return doWordBreak(s, 0, dict, memo);
    }

    public ArrayList<String> doWordBreak(String s, int start, Set<String> dict, byte[] memo) {
        ArrayList<String> result = new ArrayList<String>();
        if (start == s.length()) {
            result.add("");
            return result;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String word = s.substring(start, i);
            if ((memo[i]) != -1 && dict.contains(word)) {
                for (String wordList : doWordBreak(s, i, dict, memo)) {
                    result.add(word + (wordList.isEmpty() ? "" : " " + wordList));
                }
            }
        }

        memo[start] = (byte) (result.size() == 0 ? -1 : 1);
        return result;
    }
}

class Solution2 {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        boolean[] memo = new boolean[s.length() + 1];
        memo[s.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (memo[j] && dict.contains(s.substring(i, j)))
                    memo[i] = true;
            }
        }

        ArrayList<String> result = new ArrayList<String>();
        StringBuilder path = new StringBuilder();
        dpWordBreak(s, dict, 0, path, result, memo);
        return result;
    }

    private void dpWordBreak(String s, Set<String> dict, int start, StringBuilder path, ArrayList<String> result,
            boolean[] memo) {
        if (start == s.length())
            result.add(path.toString());

        for (int i = start + 1; i <= s.length(); i++) {
            if (memo[i] && dict.contains(s.substring(start, i))) {
                int len = path.length();
                path.append(path.length() == 0 ? s.substring(start, i) : " " + s.substring(start, i));
                dpWordBreak(s, dict, i, path, result, memo);
                path.delete(len, path.length());
            }
        }
    }
}
