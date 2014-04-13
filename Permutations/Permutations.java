public class Solution {
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
