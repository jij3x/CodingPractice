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
