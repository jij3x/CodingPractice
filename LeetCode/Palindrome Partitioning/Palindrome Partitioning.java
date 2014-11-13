public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (s.isEmpty()) {
            result.add(new ArrayList<String>());
            return result;
        }

        for (int i = 1; i <= s.length(); i++) {
            String head = s.substring(0, i);
            if (head.equals((new StringBuilder(head)).reverse().toString())) {
                for (List<String> list : partition(s.substring(i))) {
                    list.add(0, s.substring(0, i));
                    result.add(list);
                }
            }
        }
        return result;
    }
}

class Solution2 {
    public List<List<String>> partition(String s) {
        boolean[][] memo = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (j + 1 >= i - 1 || memo[j + 1][i - 1]))
                    memo[j][i] = true;
            }
        }
        return dfs(s, 0, memo);
    }

    private List<List<String>> dfs(String s, int start, boolean[][] memo) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (start == s.length())
            result.add(new ArrayList<String>());

        for (int i = start; i < s.length(); i++) {
            if (memo[start][i]) {
                for (List<String> list : dfs(s, i + 1, memo)) {
                    list.add(0, s.substring(start, i + 1));
                    result.add(list);
                }
            }
        }
        return result;
    }
}
