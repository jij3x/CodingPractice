public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        Arrays.sort(S);
        for (int e : S) {
            int len = result.size();
            for (int i = 0; i < len; i++) {
                ArrayList<Integer> row = new ArrayList<Integer>(result.get(i));
                row.add(e);
                result.add(row);
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

    private List<List<Integer>> doSubsets(int[] S, int start) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (start == S.length) {
            result.add(new ArrayList<Integer>());
            return result;
        }

        for (List<Integer> row : doSubsets(S, start + 1)) {
            result.add(row);
            ArrayList<Integer> dup = new ArrayList<Integer>(row);
            dup.add(0, S[start]);
            result.add(dup);
        }
        return result;
    }
}

class Solution3 {
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
