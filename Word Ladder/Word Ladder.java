public class Solution {
	public int ladderLength(String start, String end, HashSet<String> dict) {
		HashSet<String> visited = new HashSet<String>();
		LinkedList<String> workingQue = new LinkedList<String>();

		workingQue.add(start);
		int dist = 1, currLevelCnt = 1, nextLevelCnt = 0;
		while (!workingQue.isEmpty()) {
			String word = workingQue.poll();
			if (word.equals(end))
				return dist;

			for (int i = 0; i < word.length(); i++) {
				StringBuilder buffer = new StringBuilder(word);
				for (char c = 'a'; c <= 'z'; c++) {
					buffer.setCharAt(i, c);
					String newWord = buffer.toString();
					if (c == word.charAt(i) || !dict.contains(newWord) || visited.contains(newWord))
						continue;

					workingQue.add(newWord);
					visited.add(newWord);
					nextLevelCnt++;
				}
			}

			if (--currLevelCnt == 0) {
				currLevelCnt = nextLevelCnt;
				nextLevelCnt = 0;
				dist++;
			}
		}

		return 0;
	}
}
