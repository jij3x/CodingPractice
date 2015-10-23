public class Solution {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjTbl = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++)
            adjTbl.add(new ArrayList<Integer>());
        for (int[] edge : edges) {
            adjTbl.get(edge[0]).add(edge[1]);
            adjTbl.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        if (hasCycle(0, adjTbl, visited, -1))
            return false;

        for (int i = 0; i < n; i++) {
            if (!visited[i])
                return false;
        }
        return true;
    }

    private boolean hasCycle(int n, List<List<Integer>> adjTbl, boolean[] visited, int parent) {
        if (visited[n] == true)
            return true;

        visited[n] = true;
        for (int i = 0; i < adjTbl.get(n).size(); i++) {
            if (adjTbl.get(n).get(i) != parent) {
                if (hasCycle(adjTbl.get(n).get(i), adjTbl, visited, n))
                    return true;
            }
        }
        return false;
    }
}