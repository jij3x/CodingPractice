public class Solution {
	public String minWindow(String S, String T) {
		int[] found = new int[256];
		int[] toFind = new int[256];
		for (int i = 0; i < T.length(); i++)
			toFind[T.charAt(i)]++;

		int c = 0, start = 0, end = 0, minStart = 0, minEnd = S.length();
		for (end = 0; end < S.length(); end++) {
			if (toFind[S.charAt(end)] == 0)
				continue;

			found[S.charAt(end)]++;
			c += found[S.charAt(end)] <= toFind[S.charAt(end)] ? 1 : 0;
			while (c == T.length()) {
				if (end - start < minEnd - minStart) {
					minStart = start;
					minEnd = end;
				}
				if (toFind[S.charAt(start)] > 0) {
					found[S.charAt(start)]--;
					c -= found[S.charAt(start)] >= toFind[S.charAt(start)] ? 0 : 1;
				}
				start++;
			}
		}

		return minStart == 0 && minEnd == S.length() ? "" : S.substring(minStart, minEnd + 1);
	}
}

class Solution2 {
	class Window {
		HashMap<Character, Integer> target, current;
		int missing;

		Window(String S) {
			target = new HashMap<Character, Integer>();
			current = new HashMap<Character, Integer>();
			missing = S.length();
			for (int i = 0; i < S.length(); i++)
				target.put(S.charAt(i), target.get(S.charAt(i)) == null ? 1 : target.get(S.charAt(i)) + 1);
		}

		int add(Character c) {
			if (!target.containsKey(c))
				return missing;
			current.put(c, current.get(c) == null ? 1 : current.get(c) + 1);
			missing -= current.get(c) <= target.get(c) ? 1 : 0;
			return missing;
		}

		int remove(Character c) {
			if (!target.containsKey(c))
				return missing;
			current.put(c, current.get(c) - 1);
			missing += current.get(c) >= target.get(c) ? 0 : 1;
			return missing;
		}
	}

	public String minWindow(String S, String T) {
		Window w = new Window(T);
		int ms = 0, me = S.length();
		int start = 0, end = 0;
		while (end < S.length()) {
			if (w.add(S.charAt(end)) == 0) {
				while (start <= end) {
					if (end - start < me - ms) {
						me = end;
						ms = start;
					}
					if (w.remove(S.charAt(start++)) > 0)
						break;
				}
			}
			end++;
		}

		return ms == 0 && me == S.length() ? "" : S.substring(ms, me + 1);
	}
}