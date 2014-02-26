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
