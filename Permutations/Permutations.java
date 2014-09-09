public class Solution {
    public List<List<Integer>> permute(int[] num) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        doPermute(num, 0, result);
        return result;
    }

    private void doPermute(int[] num, int start, List<List<Integer>> result) {
        if (start == num.length - 1) {
            ArrayList<Integer> subResult = new ArrayList<Integer>();
            for (int e : num)
                subResult.add(e);
            result.add(subResult);
        }

        for (int i = start; i < num.length; i++) {
            int temp = num[start];
            num[start] = num[i];
            num[i] = temp;

            doPermute(num, start + 1, result);

            num[i] = num[start];
            num[start] = temp;
        }
    }
}

class Solution2 {
    public List<List<Integer>> permute(int[] num) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        boolean[] visited = new boolean[num.length];
        doPermute(num, visited, new ArrayList<Integer>(), result);
        return result;
    }

    private void doPermute(int[] num, boolean[] visited, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == num.length)
            result.add(new ArrayList<Integer>(path));

        for (int i = 0; i < num.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                path.add(num[i]);
                doPermute(num, visited, path, result);
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}

class Solution3 {
    public List<List<Integer>> permute(int[] num) {
        boolean[] visited = new boolean[num.length];
        return doPermute(num, visited);
    }

    private boolean allVisited(boolean[] visited) {
        for (boolean e : visited) {
            if (!e)
                return false;
        }
        return true;
    }

    private List<List<Integer>> doPermute(int[] num, boolean[] visited) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (allVisited(visited)) {
            result.add(new ArrayList<Integer>());
            return result;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            for (List<Integer> subResult : doPermute(num, visited)) {
                subResult.add(0, num[i]);
                result.add(subResult);
            }
            visited[i] = false;
        }
        return result;
    }
}
