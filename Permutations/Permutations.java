public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        doPermute(num, 0, result);
        return result;
    }

    private void doPermute(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
        if (start == num.length - 1) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i : num)
                list.add(i);
            result.add(list);
            return;
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
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        boolean[] visited = new boolean[num.length];

        doPermute(num, visited, path, result);
        return result;
    }

    private void doPermute(int[] num, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if (path.size() == num.length)
            result.add(new ArrayList<Integer>(path));

        for (int i = 0; i < num.length; i++) {
            if (!visited[i]) {
                path.add(num[i]);
                visited[i] = true;
                doPermute(num, visited, path, result);
                path.remove(path.size() - 1);
                visited[i] = false;
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
