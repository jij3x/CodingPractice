public class Solution {
	public int minCut(String s) {
		boolean[][] palindromeMemo = new boolean[s.length()][s.length()];
		int[] minCutMemo = new int[s.length()];

		for (int i = 0; i < s.length(); i++) {
			minCutMemo[i] = i + 1;
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

class Solution2 {
	public int minCut(String s) {
		boolean[][] palindromeMemo = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j >= 0; j--) {
				if (s.charAt(j) == s.charAt(i) && (i - j <= 1 || palindromeMemo[j + 1][i - 1]))
					palindromeMemo[j][i] = true;
			}
		}

		Queue<Integer> workingQue = new LinkedList<Integer>();
		boolean[] visited = new boolean[s.length() + 1];
		workingQue.add(0);
		visited[0] = true;
		int currLvlCnt = 1, nextLvlCnt = 0, level = 0;
		while (currLvlCnt > 0) {
			int ps = workingQue.poll();
			currLvlCnt--;
			if (ps == s.length())
				return level - 1;

			for (int i = ps; i < s.length(); i++) {
				if (palindromeMemo[ps][i] && !visited[i + 1]) {
					workingQue.add(i + 1);
					visited[i + 1] = true;
					nextLvlCnt++;
				}
			}

			if (currLvlCnt == 0) {
				currLvlCnt = nextLvlCnt;
				nextLvlCnt = 0;
				level++;
			}
		}

		return 0;
	}
}
