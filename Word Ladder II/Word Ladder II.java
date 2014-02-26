public class Solution {
	public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
		dict.add(start);
		dict.add(end);
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		HashMap<String, ArrayList<String>> visited = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> exploring = new HashMap<String, ArrayList<String>>();

		LinkedList<String> workingQue = new LinkedList<String>();
		workingQue.add(start);
		visited.put(start, null);
		int currLevelCnt = 1, nextLevelCnt = 0;
		while (currLevelCnt > 0) {
			String word = workingQue.poll();
			if (word.equals(end)) {
				ArrayList<String> path = new ArrayList<String>();
				dfs(end, start, visited, path, result);
				return result;
			}

			for (int i = 0; i < word.length(); i++) {
				StringBuilder buffer = new StringBuilder(word);
				for (char c = 'a'; c <= 'z'; c++) {
					buffer.setCharAt(i, c);
					String newStr = buffer.toString();
					if (c == word.charAt(i) || !dict.contains(newStr) || visited.containsKey(newStr))
						continue;

					ArrayList<String> prevList;
					if (exploring.containsKey(newStr)) {
						prevList = exploring.get(newStr);
					} else {
						workingQue.add(newStr);
						nextLevelCnt++;
						prevList = new ArrayList<String>();
					}
					prevList.add(word);
					exploring.put(newStr, prevList);
				}
			}

			if (--currLevelCnt == 0) {
				for (String k : exploring.keySet())
					visited.put(k, exploring.get(k));
				exploring.clear();

				currLevelCnt = nextLevelCnt;
				nextLevelCnt = 0;
			}
		}
		return result;
	}

	private void dfs(String end, String start, HashMap<String, ArrayList<String>> visited, ArrayList<String> path,
			ArrayList<ArrayList<String>> result) {
		if (end == start) {
			ArrayList<String> subResult = new ArrayList<String>(path);
			subResult.add(end);
			Collections.reverse(subResult);
			result.add(subResult);
			return;
		}

		path.add(end);
		ArrayList<String> prevList = visited.get(end);
		for (int i = 0; i < prevList.size(); i++)
			dfs(prevList.get(i), start, visited, path, result);
		path.remove(path.size() - 1);
	}
}

class Solution2 {
	public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
		dict.add(start);
		dict.add(end);
		HashMap<String, ArrayList<String>> adjList = buildAdjList(dict);

		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		HashMap<String, ArrayList<String>> visited = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> exploring = new HashMap<String, ArrayList<String>>();

		LinkedList<String> workingQue = new LinkedList<String>();
		workingQue.add(start);
		visited.put(start, null);
		int currLevelCnt = 1, nextLevelCnt = 0;
		while (currLevelCnt > 0) {
			String word = workingQue.poll();
			if (word.equals(end)) {
				ArrayList<String> path = new ArrayList<String>();
				dfs(end, start, visited, path, result);
				return result;
			}

			for (String next : adjList.get(word)) {
				if (visited.containsKey(next))
					continue;

				ArrayList<String> prevList;
				if (exploring.containsKey(next)) {
					prevList = exploring.get(next);
				} else {
					workingQue.add(next);
					nextLevelCnt++;
					prevList = new ArrayList<String>();
				}
				prevList.add(word);
				exploring.put(next, prevList);
			}

			if (--currLevelCnt == 0) {
				for (String k : exploring.keySet())
					visited.put(k, exploring.get(k));
				exploring.clear();

				currLevelCnt = nextLevelCnt;
				nextLevelCnt = 0;
			}
		}
		return result;
	}

	private HashMap<String, ArrayList<String>> buildAdjList(HashSet<String> dict) {
		ArrayList<String> words = new ArrayList<String>(dict);
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();

		for (int i = 0; i < words.get(0).length(); i++) {
			WordComparator wordComparator = new WordComparator(i);
			Collections.sort(words, wordComparator);
			for (int j = 0, left = 0, right = 0; j < words.size(); j++) {
				if (j == words.size() - 1 || wordComparator.compare(words.get(left), words.get(j + 1)) != 0) {
					right = j;
					for (int k = left; k <= right; k++) {
						ArrayList<String> neighbors;
						if (result.containsKey(words.get(k)))
							neighbors = result.get(words.get(k));
						else
							neighbors = new ArrayList<String>();

						for (int l = left; l <= right; l++) {
							if (l != k)
								neighbors.add(words.get(l));
						}

						result.put(words.get(k), neighbors);
					}

					left = right + 1;
				}
			}
		}
		return result;
	}

	private class WordComparator implements Comparator<String> {
		private int ignoreIdx;

		WordComparator(int idx) {
			ignoreIdx = idx;
		}

		@Override
		public int compare(String a, String b) {
			for (int i = 0; i < a.length(); i++) {
				if (i != ignoreIdx && a.charAt(i) != b.charAt(i))
					return a.charAt(i) - b.charAt(i);
			}
			return 0;
		}
	}

	private void dfs(String end, String start, HashMap<String, ArrayList<String>> visited, ArrayList<String> path,
			ArrayList<ArrayList<String>> result) {
		if (end == start) {
			ArrayList<String> subResult = new ArrayList<String>(path);
			subResult.add(end);
			Collections.reverse(subResult);
			result.add(subResult);
			return;
		}

		path.add(end);
		ArrayList<String> prevList = visited.get(end);
		for (int i = 0; i < prevList.size(); i++)
			dfs(prevList.get(i), start, visited, path, result);
		path.remove(path.size() - 1);
	}
}
