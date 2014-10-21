public class Solution {
    public int minCut(String s) {
        boolean[][] palTbl = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 1 || palTbl[j + 1][i - 1]))
                    palTbl[j][i] = true;
            }
        }

        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dpMinPatitions(s, 0, palTbl, memo) - 1;
    }

    private int dpMinPatitions(String s, int start, boolean[][] palTbl, int[] memo) {
        if (start == s.length())
            return 0;

        if (memo[start] != -1)
            return memo[start];

        int min = s.length() - start;
        for (int i = start; i < s.length(); i++) {
            if (palTbl[start][i])
                min = Math.min(min, dpMinPatitions(s, i + 1, palTbl, memo));
        }

        memo[start] = min + 1;
        return memo[start];
    }
}

class Solution2 {
    public int minCut(String s) {
        boolean[][] palTbl = new boolean[s.length()][s.length()];
        int[] minPal = new int[s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            minPal[i + 1] = i + 1;
            for (int j = i; j >= 0; j--) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 1 || palTbl[j + 1][i - 1])) {
                    palTbl[j][i] = true;
                    minPal[i + 1] = Math.min(minPal[i + 1], minPal[j] + 1);
                }
            }
        }

        return minPal[s.length()] - 1;
    }
}

class Solution3 {
    public int minCut(String s) {
        boolean[][] palTbl = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i || (s.charAt(j) == s.charAt(i) && (j == i - 1 || palTbl[j + 1][i - 1])))
                    palTbl[j][i] = true;
            }
        }

        Queue<Integer> que = new LinkedList<Integer>();
        boolean[] visited = new boolean[s.length() + 1];
        que.add(0);
        visited[0] = true;
        int currLvlCnt = 1, nextLvlCnt = 0, level = 0;
        while (currLvlCnt > 0) {
            int ps = que.poll();
            currLvlCnt--;
            if (ps == s.length())
                return level - 1;

            for (int i = ps; i < s.length(); i++) {
                if (palTbl[ps][i] && !visited[i + 1]) {
                    que.add(i + 1);
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
