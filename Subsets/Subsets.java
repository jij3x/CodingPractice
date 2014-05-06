public class Solution {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        result.add(new ArrayList<Integer>());

        Arrays.sort(S);
        for (int i = 0; i < S.length; i++) {
            int len = result.size();
            for (int j = 0; j < len; j++) {
                ArrayList<Integer> r = new ArrayList<Integer>(result.get(j));
                r.add(S[i]);
                result.add(r);
            }
        }
        return result;
    }
}

class Solution2 {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        return doSubset(S, 0);
    }

    private ArrayList<ArrayList<Integer>> doSubset(int[] S, int idx) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        result.add(new ArrayList<Integer>());
        if (idx == S.length)
            return result;

        for (int i = idx; i < S.length; i++) {
            for (ArrayList<Integer> r : doSubset(S, i + 1)) {
                r.add(0, S[i]);
                result.add(r);
            }
        }
        return result;
    }
}
