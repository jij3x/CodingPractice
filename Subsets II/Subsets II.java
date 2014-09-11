public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
        return dfs(num, 0);
    }

    private List<List<Integer>> dfs(int[] num, int start) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        if (start == num.length)
            return result;

        for (int i = start; i < num.length; i++) {
            for (List<Integer> row : dfs(num, i + 1)) {
                row.add(0, num[i]);
                result.add(row);
            }

            while (i < num.length - 1 && num[i] == num[i + 1])
                i++;
        }
        return result;
    }
}
