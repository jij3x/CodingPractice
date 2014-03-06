public class Solution {
	public int minCut(String s) {
		boolean[][] palindromeMemo = new boolean[s.length()][s.length()];
		int[] minCutMemo = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			minCutMemo[i] = i + 1;
		}

		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j >= 0; j--) {
				if (j == i || (s.charAt(j) == s.charAt(i) && (j == i - 1 || palindromeMemo[j + 1][i - 1]))) {
					palindromeMemo[j][i] = true;
					minCutMemo[i] = Math.min(minCutMemo[i], j == 0 ? 1 : minCutMemo[j - 1] + 1);
				}
			}
		}
		return minCutMemo[s.length() - 1] - 1;
	}
}

/*
 * Very slow.
 */
class Solution2 {
	public int minCut(String s) {
		boolean[][] palindromeMemo = new boolean[s.length()][s.length()];
		palindromeMemo[s.length() - 1][s.length() - 1] = true;
		for (int i = 0; i < s.length() - 1; i++) {
			palindromeMemo[i][i] = true;
			palindromeMemo[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
		}
		for (int i = 2; i < s.length(); i++) {
			for (int j = i - 2; j >= 0; j--) {
				palindromeMemo[j][i] = (s.charAt(j) == s.charAt(i)) && palindromeMemo[j + 1][i - 1];
			}
		}

		int[][] minCutMemo = new int[s.length()][s.length()];
		findMinCut(s, 0, s.length() - 1, minCutMemo, palindromeMemo);
		return minCutMemo[0][s.length() - 1];
	}

	private void findMinCut(String s, int start, int end, int[][] minCutMemo, boolean[][] palindromeMemo) {
		if (minCutMemo[start][end] != 0)
			return;
		if (palindromeMemo[start][end]) {
			minCutMemo[start][end] = 1;
			return;
		}

		int minCut = end - start + 1;
		for (int i = start; i < end; i++) {
			findMinCut(s, start, i, minCutMemo, palindromeMemo);
			findMinCut(s, i + 1, end, minCutMemo, palindromeMemo);
			minCut = Math.min(minCut, minCutMemo[start][i] + minCutMemo[i + 1][end]);
		}
		minCutMemo[start][end] = minCut;
	}
}
