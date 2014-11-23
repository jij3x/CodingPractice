public class Solution {
    public String getPermutation(int n, int k) {
        int[] fTbl = new int[n + 1];
        for (int i = 0; i <= n; i++)
            fTbl[i] = i < 2 ? 1 : fTbl[i - 1] * i;

        return dfs(n, k, new boolean[n], fTbl);
    }

    private String dfs(int n, int k, boolean[] visited, int[] fTbl) {
        if (n == 0)
            return "";

        for (int i = 0, seg = (k - 1) / fTbl[n - 1]; i < visited.length; i++) {
            if (!visited[i] && --seg < 0) {
                visited[i] = true;
                return Integer.toString(1 + i) + dfs(n - 1, (k - 1) % fTbl[n - 1] + 1, visited, fTbl);
            }
        }
        return "";
    }
}

class Solution2 {
    public String getPermutation(int n, int k) {
        int[] fTbl = new int[n + 1];
        for (int i = 0; i <= n; i++)
            fTbl[i] = i < 2 ? 1 : fTbl[i - 1] * i;

        boolean[] visited = new boolean[n];
        int result = 0;
        for (; n > 0; k = (k - 1) % fTbl[n - 1] + 1, n--) {
            for (int i = 0, seg = (k - 1) / fTbl[n - 1]; i < visited.length; i++) {
                if (!visited[i] && --seg < 0) {
                    visited[i] = true;
                    result = result * 10 + i + 1;
                    break;
                }
            }
        }
        return Integer.toString(result);
    }
}
