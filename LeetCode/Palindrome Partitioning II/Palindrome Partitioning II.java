public class Solution {
    public int minCut(String s) {
        boolean[][] plndTbl = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (j + 1 > i - 1 || plndTbl[j + 1][i - 1]))
                    plndTbl[j][i] = true;
            }
        }

        int[] memo = new int[s.length()];
        return dpMinCut(s, 0, plndTbl, memo) - 1;
    }

    private int dpMinCut(String s, int start, boolean[][] plndTbl, int[] memo) {
        if (start == s.length())
            return 1;

        if (memo[start] == 0) {
            memo[start] = s.length() - start;
            for (int i = start; i < s.length(); i++) {
                if (plndTbl[start][i])
                    memo[start] = Math.min(memo[start], dpMinCut(s, i + 1, plndTbl, memo) + 1);
            }
        }
        return memo[start];
    }
}

class Solution2 {
    public int minCut(String s) {
        boolean[][] plndTbl = new boolean[s.length()][s.length()];
        int[] minTbl = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            minTbl[i + 1] = i + 1;
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (j + 1 > i - 1 || plndTbl[j + 1][i - 1])) {
                    plndTbl[j][i] = true;
                    minTbl[i + 1] = Math.min(minTbl[i + 1], minTbl[j] + 1);
                }
            }
        }
        return minTbl[s.length()] - 1;
    }
}

class Solution3 {
    public int minCut(String s) {
        boolean[][] plndTbl = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (j + 1 > i - 1 || plndTbl[j + 1][i - 1]))
                    plndTbl[j][i] = true;
            }
        }

        Queue<Integer> que = new LinkedList<Integer>();
        boolean[] visited = new boolean[s.length() + 1];
        int currLvl = 1, nextLvl = 0, dist = 0;
        que.add(0);
        visited[0] = true;
        while (!que.isEmpty()) {
            int start = que.poll();
            if (start == s.length())
                return dist - 1;

            for (int i = start; i < s.length(); i++) {
                if (plndTbl[start][i] && !visited[i + 1]) {
                    visited[i + 1] = true;
                    que.add(i + 1);
                    nextLvl++;
                }
            }

            if (--currLvl == 0) {
                currLvl = nextLvl;
                nextLvl = 0;
                dist++;
            }
        }
        return 0;
    }
}
