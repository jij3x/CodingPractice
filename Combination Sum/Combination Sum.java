public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return doCombSum(candidates, target, 0);
    }

    private List<List<Integer>> doCombSum(int[] num, int target, int start) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();

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
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        subCombinationSum(candidates, target, 0, path, result);
        return result;
    }

    private void subCombinationSum(int[] candidates, int target, int idx, ArrayList<Integer> path,
            ArrayList<ArrayList<Integer>> result) {
        for (int i = idx; i < candidates.length; i++) {
            if (candidates[i] == target) {
                ArrayList<Integer> r = new ArrayList<Integer>(path);
                r.add(candidates[i]);
                Collections.sort(r);
                result.add(r);
            } else if (candidates[i] < target) {
                path.add(candidates[i]);
                subCombinationSum(candidates, target - candidates[i], i, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}
