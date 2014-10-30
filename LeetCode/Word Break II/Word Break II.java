public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        ArrayList<ArrayList<String>> memo = new ArrayList<ArrayList<String>>();
        for (int i = 0; i <= s.length(); i++)
            memo.add(new ArrayList<String>());
        memo.get(s.length()).add("");

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                String word = s.substring(i, j + 1);
                if (dict.contains(word) && memo.get(j + 1).size() > 0) {
                    for (String wdList : memo.get(j + 1))
                        memo.get(i).add(word + (wdList.isEmpty() ? "" : " ") + wdList);
                }
            }
        }
        return memo.get(0);
    }
}
