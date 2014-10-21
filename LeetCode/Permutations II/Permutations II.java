public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        Arrays.sort(num);

        boolean[] visited = new boolean[num.length];
        ArrayList<Integer> path = new ArrayList<Integer>();
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        doPermuteUnique(num, visited, path, result);
        return result;
    }

    private void doPermuteUnique(int[] num, boolean[] visited, ArrayList<Integer> path, ArrayList<List<Integer>> result) {
        if (path.size() == num.length)
            result.add(new ArrayList<Integer>(path));

        for (int i = 0; i < num.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                path.add(num[i]);
                doPermuteUnique(num, visited, path, result);
                path.remove(path.size() - 1);
                visited[i] = false;

                while (i < num.length - 1 && num[i] == num[i + 1])
                    i++;
            }
        }
    }
}

public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        Arrays.sort(num);
        return dfs(num, new boolean[num.length]);
    }

    private List<List<Integer>> dfs(int[] num, boolean[] visited) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (allVisited(visited))
            result.add(new ArrayList<Integer>());

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && num[i - 1] == num[i]))
                continue;

            visited[i] = true;
            for (List<Integer> row : dfs(num, visited)) {
                row.add(0, num[i]);
                result.add(row);
            }
            visited[i] = false;
        }
        return result;
    }

    private boolean allVisited(boolean[] visited) {
        for (boolean e : visited) {
            if (!e)
                return false;
        }
        return true;
    }
}
