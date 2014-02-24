/*
 * n = S.length()
 * m = L.length
 * l = L[0].length()
 * 
 * O(n*m*l)
 */
public class Solution {
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		int w = L[0].length();
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		for (int i = 0; i < L.length; i++) {
			dict.put(L[i], dict.containsKey(L[i]) ? dict.get(L[i]) + 1 : 1);
		}

		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i + L.length * w <= S.length(); i++) {
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
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		int w = L[0].length();
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		for (int i = 0; i < L.length; i++) {
			dict.put(L[i], dict.containsKey(L[i]) ? dict.get(L[i]) + 1 : 1);
		}

		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < w; i++) {
			HashMap<String, Integer> memo = new HashMap<String, Integer>();
			for (int j = i, left = j, missing = L.length; j + w <= S.length(); j += w) {
				String curr = S.substring(j, j + w);

				if (!dict.containsKey(curr)) {
					missing = L.length;
					memo.clear();
					continue;
				}

				left = missing == L.length ? j : left;
				memo.put(curr, memo.containsKey(curr) ? memo.get(curr) + 1 : 1);
				if (memo.get(curr) > dict.get(curr)) {
					for (; left <= j; left += w) {
						String toDrop = S.substring(left, left + w);
						if (toDrop.equals(curr)) {
							memo.put(curr, memo.get(curr) - 1);
							left += w;
							break;
						}
						memo.put(toDrop, memo.get(toDrop) - 1);
						missing++;
					}
					continue;
				}

				if (--missing == 0) {
					result.add(left);
					String toDrop = S.substring(left, left + w);
					left += w;
					memo.put(toDrop, memo.get(toDrop) - 1);
					missing++;
				}
			}
		}
		return result;
	}
}
