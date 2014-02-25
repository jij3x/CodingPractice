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
				for (char x = 'a'; x <= 'z'; x++) {
					buffer.setCharAt(i, x);
					String newWord = buffer.toString();
					if (x == word.charAt(i) || !dict.contains(newWord) || visited.contains(newWord))
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

class Solution2 {
	public int ladderLength(String start, String end, HashSet<String> dict) {
		HashMap<String, ArrayList<String>> adjList = buildAdjList(dict);

		HashSet<String> visited = new HashSet<String>();
		LinkedList<String> workingQue = new LinkedList<String>();
		workingQue.add(start);
		int dist = 1, currLevelCnt = 1, nextLevelCnt = 0;
		while (!workingQue.isEmpty()) {
			String word = workingQue.poll();
			if (word.equals(end))
				return dist;

			ArrayList<String> neighbors = adjList.get(word);
			for (String next : neighbors) {
				if (!visited.contains(next)) {
					workingQue.add(next);
					visited.add(next);
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

	private HashMap<String, ArrayList<String>> buildAdjList(HashSet<String> dict) {
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		for (String word : dict) {
			ArrayList<String> neighbors = new ArrayList<String>();
			for (int i = 0; i < word.length(); i++) {
				StringBuilder buffer = new StringBuilder(word);
				for (char c = 'a'; c <= 'z'; c++) {
					if (c != word.charAt(i)) {
						buffer.setCharAt(i, c);
						if (dict.contains(buffer.toString()))
							neighbors.add(buffer.toString());
					}
				}
			}
			result.put(word, neighbors);
		}

		return result;
	}
}
