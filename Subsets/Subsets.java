public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        Arrays.sort(S);
        for (int i = 0; i < S.length; i++) {
            int len = result.size();
            for (int j = 0; j < len; j++) {
                List<Integer> r = new ArrayList<Integer>(result.get(j));
                r.add(S[i]);
                result.add(r);
            }
        }
        return result;
    }
}

class Solution2 {
    public List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        return doSubsets(S, 0);
    }

    private List<List<Integer>> doSubsets(int[] S, int idx) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        if (idx == S.length)
            return result;

        for (int i = idx; i < S.length; i++) {
            for (List<Integer> r : doSubsets(S, i + 1)) {
                r.add(0, S[i]);
                result.add(r);
            }
        }
        return result;
    }
}
