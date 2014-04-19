public class Solution {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        Arrays.sort(num);

        boolean[] visited = new boolean[num.length];
        ArrayList<Integer> path = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        doPermuteUnique(num, visited, path, result);
        return result;
    }

    private void doPermuteUnique(int[] num, boolean[] visited, ArrayList<Integer> path,
            ArrayList<ArrayList<Integer>> result) {
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
