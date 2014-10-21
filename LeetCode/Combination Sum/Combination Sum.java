public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return doCombSum(candidates, target, 0);
    }

    private List<List<Integer>> doCombSum(int[] num, int target, int start) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (int i = start; i < num.length; i++) {
            if (num[i] == target) {
                result.add(new ArrayList<Integer>(Arrays.asList(num[i])));
            } else if (num[i] < target) {
                for (List<Integer> row : doCombSum(num, target - num[i], i)) {
                    row.add(num[i]);
                    Collections.sort(row);
                    result.add(row);
                }
            }
        }
        return result;
    }
}

class Solution2 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        doCombSum(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void doCombSum(int[] num, int target, int start, List<Integer> path, List<List<Integer>> result) {
        for (int i = start; i < num.length; i++) {
            if (num[i] == target) {
                ArrayList<Integer> r = new ArrayList<Integer>(path);
                r.add(num[i]);
                result.add(r);
            } else if (num[i] < target) {
                path.add(num[i]);
                doCombSum(num, target - num[i], i, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}
