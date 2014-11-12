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
    public ArrayList<ArrayList<String>> partition(String s) {
        boolean[][] memo = new boolean[s.length()][s.length()];
        memo[s.length() - 1][s.length() - 1] = true;
        for (int i = 0; i < s.length() - 1; i++) {
            memo[i][i] = true;
            memo[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        for (int i = 2; i < s.length(); i++) {
            for (int j = i - 2; j >= 0; j--) {
                memo[j][i] = (s.charAt(j) == s.charAt(i)) && memo[j + 1][i - 1];
            }
        }

        return doPartition(s, 0, memo);
    }

    private ArrayList<ArrayList<String>> doPartition(String s, int start, boolean[][] memo) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if (start == s.length()) {
            result.add(new ArrayList<String>());
            return result;
        }

        for (int i = start; i < s.length(); i++) {
            if (memo[start][i]) {
                for (ArrayList<String> r : doPartition(s, i + 1, memo)) {
                    ArrayList<String> row = new ArrayList<String>();
                    row.add(s.substring(start, i + 1));
                    row.addAll(r);
                    result.add(row);
                }
            }
        }
        return result;
    }
}
