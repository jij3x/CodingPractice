public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        Queue<String> que = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        que.add(start);
        int currLvl = 1, nextLvl = 0, level = 1;
        visited.add(start);
        while (currLvl != 0) {
            String curr = que.poll();
            currLvl--;
            if (curr.equals(end))
                return level;

            for (int i = 0; i < curr.length(); i++) {
                StringBuilder buffer = new StringBuilder(curr);
                for (char c = 'a'; c <= 'z'; c++) {
                    buffer.setCharAt(i, c);
                    String word = buffer.toString();
                    if (!word.equals(curr) && dict.contains(word) && !visited.contains(word)) {
                        visited.add(word);
                        que.add(word);
                        nextLvl++;
                    }
                }
            }

            if (currLvl == 0) {
                currLvl = nextLvl;
                nextLvl = 0;
                level++;
            }
        }
        return 0;
    }
}
