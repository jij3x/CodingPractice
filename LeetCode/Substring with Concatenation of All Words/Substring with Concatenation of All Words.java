/*
 * n = S.length()
 * m = L.length
 * l = L[0].length()
 * 
 * O((n-m*l)*m*l)
 */
public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        for (String s : L)
            dict.put(s, dict.containsKey(s) ? dict.get(s) + 1 : 1);

        int w = L[0].length();
        for (int i = 0; i + w * L.length <= S.length(); i++) {
            HashMap<String, Integer> memo = new HashMap<String, Integer>();
            for (int j = 0; j < L.length; j++) {
                String curr = S.substring(i + j * w, i + j * w + w);
                if (!dict.containsKey(curr))
                    break;

                memo.put(curr, memo.containsKey(curr) ? memo.get(curr) + 1 : 1);
                if (memo.get(curr) > dict.get(curr))
                    break;

                if (j == L.length - 1)
                    result.add(i);
            }
        }
        return result;
    }
}

/*
 * O(n)
 */
class Solution2 {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> result = new ArrayList<Integer>();
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        for (String s : L)
            dict.put(s, dict.containsKey(s) ? dict.get(s) + 1 : 1);

        for (int i = 0, wl = L[0].length(); i < wl; i++) {
            HashMap<String, Integer> memo = new HashMap<String, Integer>();
            for (int missing = L.length, right = i, left = right; right + wl <= S.length(); right += wl) {
                String word = S.substring(right, right + wl);
                if (!dict.containsKey(word)) {
                    left = right + wl;
                    missing = L.length;
                    memo.clear();
                    continue;
                }

                memo.put(word, memo.containsKey(word) ? memo.get(word) + 1 : 1);
                if (memo.get(word) > dict.get(word)) {
                    for (; left <= right; missing++) {
                        String toDrop = S.substring(left, left + wl);
                        memo.put(toDrop, memo.get(toDrop) - 1);
                        left += wl;
                        if (toDrop.equals(word))
                            break;
                    }
                } else if (--missing == 0) {
                    result.add(left);
                    String toDrop = S.substring(left, left + wl);
                    left += wl;
                    memo.put(toDrop, memo.get(toDrop) - 1);
                    missing++;
                }
            }
        }
        return result;
    }
}
