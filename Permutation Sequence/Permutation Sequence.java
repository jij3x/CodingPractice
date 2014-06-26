public class Solution {
    public String getPermutation(int n, int k) {
        int[] fTbl = new int[n + 1];
        fTbl[0] = fTbl[1] = 1;
        for (int i = 2; i <= n; i++)
            fTbl[i] = i * fTbl[i - 1];

        boolean[] visited = new boolean[n + 1];
        int result = 0;
        while (n > 0) {
            int seg = (k - 1) / fTbl[n - 1] + 1;
            for (int i = 1; i < visited.length; i++) {
                if (!visited[i] && --seg == 0) {
                    visited[i] = true;
                    result = result * 10 + i;
                    break;
                }
            }
            k = (k - 1) % fTbl[n - 1] + 1;
            n--;
        }
        return Integer.toString(result);
    }
}

class Solution2 {
    public String getPermutation(int n, int k) {
        int[] fTbl = new int[n + 1];
        fTbl[0] = fTbl[1] = 1;
        for (int i = 2; i <= n; i++)
            fTbl[i] = i * fTbl[i - 1];

        boolean[] visited = new boolean[n + 1];
        return doGetPerm(n, k, fTbl, visited);
    }

    private String doGetPerm(int n, int k, int[] fTbl, boolean[] visited) {
        if (n == 0)
            return "";

        int seg = (k - 1) / fTbl[n - 1] + 1;
        String num = "";
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i] && --seg == 0) {
                num = Integer.toString(i);
                visited[i] = true;
                break;
            }
        }
        return num + doGetPerm(n - 1, (k - 1) % fTbl[n - 1] + 1, fTbl, visited);
    }
}
