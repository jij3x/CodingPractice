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
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        boolean[] visited = new boolean[num.length];
        return doPermute(num, visited);
    }

    private ArrayList<ArrayList<Integer>> doPermute(int[] num, boolean[] visited) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        boolean allVisited = true;
        for (boolean v : visited) {
            if (!v) {
                allVisited = false;
                break;
            }
        }
        if (allVisited) {
            result.add(new ArrayList<Integer>());
            return result;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            for (ArrayList<Integer> r : doPermute(num, visited)) {
                r.add(0, num[i]);
                result.add(r);
            }
            visited[i] = false;
        }
        return result;
    }
}
