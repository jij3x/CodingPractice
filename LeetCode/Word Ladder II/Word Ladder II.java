public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        dict.add(start);
        dict.add(end);
        ArrayList<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> visited = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<String>> exploring = new HashMap<String, ArrayList<String>>();

        LinkedList<String> que = new LinkedList<String>();
        que.add(start);
        visited.put(start, null);
        for (int currLvl = 1, nextLvl = 0; currLvl != 0;) {
            String curr = que.poll();
            if (curr.equals(end)) {
                dfs(curr, new LinkedList<String>(), visited, result);
                return result;
            }

            for (int i = 0; i < curr.length(); i++) {
                StringBuilder buffer = new StringBuilder(curr);
                for (char c = 'a'; c <= 'z'; c++) {
                    buffer.setCharAt(i, c);
                    String newWord = buffer.toString();
                    if (dict.contains(newWord) && !visited.containsKey(newWord) && !newWord.equals(curr)) {
                        ArrayList<String> prevList = exploring.get(newWord);
                        if (prevList == null) {
                            exploring.put(newWord, prevList = new ArrayList<String>());
                            que.add(newWord);
                            nextLvl++;
                        }
                        prevList.add(curr);
                    }
                }
            }

            if (--currLvl == 0) {
                currLvl = nextLvl;
                nextLvl = 0;
                visited.putAll(exploring);
                exploring.clear();
            }
        }
        return result;
    }

    private void dfs(String word, LinkedList<String> path, HashMap<String, ArrayList<String>> visited,
            ArrayList<List<String>> result) {
        path.addFirst(word);
        ArrayList<String> prevList = visited.get(word);
        if (prevList == null) {
            result.add(new ArrayList<String>(path));
        } else {
            for (String prev : prevList) {
                dfs(prev, path, visited, result);
            }
        }
        path.remove(word);
    }
}

class Solution2 {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        dict.add(start);
        dict.add(end);
        HashMap<String, ArrayList<String>> adjList = buildAdjList(dict);

        ArrayList<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> visited = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<String>> exploring = new HashMap<String, ArrayList<String>>();

        LinkedList<String> que = new LinkedList<String>();
        que.add(start);
        visited.put(start, null);
        for (int currLvl = 1, nextLvl = 0; currLvl > 0;) {
            String word = que.poll();
            if (word.equals(end)) {
                dfs(end, start, visited, new ArrayList<String>(), result);
                return result;
            }

            for (String next : adjList.get(word)) {
                if (visited.containsKey(next))
                    continue;

                ArrayList<String> prevList;
                if (exploring.containsKey(next)) {
                    prevList = exploring.get(next);
                } else {
                    que.add(next);
                    nextLvl++;
                    prevList = new ArrayList<String>();
                    exploring.put(next, prevList);
                }
                prevList.add(word);
            }

            if (--currLvl == 0) {
                visited.putAll(exploring);
                exploring.clear();

                currLvl = nextLvl;
                nextLvl = 0;
            }
        }
        return result;
    }

    private HashMap<String, ArrayList<String>> buildAdjList(Set<String> dict) {
        ArrayList<String> words = new ArrayList<String>(dict);
        HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();

        for (int i = 0; i < words.get(0).length(); i++) {
            WordComparator wordComparator = new WordComparator(i);
            Collections.sort(words, wordComparator);
            for (int j = 0, left = 0, right = 0; j < words.size(); j++) {
                if (j == words.size() - 1 || wordComparator.compare(words.get(left), words.get(j + 1)) != 0) {
                    right = j;
                    for (int k = left; k <= right; k++) {
                        ArrayList<String> neighbors = result.get(words.get(k));
                        if (neighbors == null) {
                            neighbors = new ArrayList<String>();
                            result.put(words.get(k), neighbors);
                        }

                        for (int l = left; l <= right; l++) {
                            if (l != k)
                                neighbors.add(words.get(l));
                        }
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
            ArrayList<List<String>> result) {
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
