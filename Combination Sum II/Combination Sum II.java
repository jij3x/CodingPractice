public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        return subCombinationSum2(num, target, 0);
    }

    private ArrayList<ArrayList<Integer>> subCombinationSum2(int[] num, int target, int idx) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        for (int i = idx; i < num.length; i++) {
            if (num[i] == target) {
                result.add(new ArrayList<Integer>(Arrays.asList(num[i])));
            } else if (num[i] < target) {
                for (ArrayList<Integer> r : subCombinationSum2(num, target - num[i], i + 1)) {
                    r.add(num[i]);
                    Collections.sort(r);
                    result.add(r);
                }
            }

            while (i + 1 < num.length && num[i] == num[i + 1])
                i++;
        }

        return result;
    }
}
