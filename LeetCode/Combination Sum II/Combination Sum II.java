public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        return doCombSum(num, target, 0);
    }

    private List<List<Integer>> doCombSum(int[] num, int target, int start) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = start; i < num.length; i++) {
            if (num[i] == target) {
                result.add(new LinkedList<Integer>(Arrays.asList(num[i])));
            } else if (num[i] < target) {
                for (List<Integer> row : doCombSum(num, target - num[i], i + 1)) {
                    row.add(0, num[i]);
                    result.add(row);
                }
            }

            while (i < num.length - 1 && num[i] == num[i + 1])
                i++;
        }
        return result;
    }
}
