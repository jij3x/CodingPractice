public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        return subCombinationSum(candidates, target, 0);
    }

    private ArrayList<ArrayList<Integer>> subCombinationSum(int[] candidates, int target, int idx) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        for (int i = idx; i < candidates.length; i++) {
            if (candidates[i] == target) {
                result.add(new ArrayList<Integer>(Arrays.asList(candidates[i])));
            } else if (candidates[i] < target) {
                for (ArrayList<Integer> r : subCombinationSum(candidates, target - candidates[i], i)) {
                    r.add(candidates[i]);
                    Collections.sort(r);
                    result.add(r);
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
