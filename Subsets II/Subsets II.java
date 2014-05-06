public class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] S) {
        Arrays.sort(S);
        return doSubset(S, 0);
    }

    private ArrayList<ArrayList<Integer>> doSubset(int[] S, int start) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        result.add(new ArrayList<Integer>());
        if (start == S.length)
            return result;

        for (int i = start; i < S.length; i++) {
            for (ArrayList<Integer> r : doSubset(S, i + 1)) {
                r.add(0, S[i]);
                result.add(r);
            }

            while (i < S.length - 1 && S[i] == S[i + 1])
                i++;
        }
        return result;
    }
}
