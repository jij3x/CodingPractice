public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);

        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && num[i] == num[i - 1])
                continue;

            for (int j = i + 1; j < num.length; j++) {
                if (j > i + 1 && num[j] == num[j - 1])
                    continue;

                for (int start = j + 1, end = num.length - 1, tgt = target - num[i] - num[j]; start < end;) {
                    if (num[start] + num[end] == tgt) {
                        Integer[] row = { num[i], num[j], num[start++], num[end--] };
                        result.add(new ArrayList<Integer>(Arrays.asList(row)));

                        while (start < end && num[start] == num[start - 1])
                            start++;
                        while (start < end && num[end] == num[end + 1])
                            end--;
                    } else if (num[start] + num[end] > tgt) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        return result;
    }
}

class Solution2 {
    private class Pair {
        int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public List<List<Integer>> fourSum(int[] num, int target) {
        HashMap<Integer, ArrayList<Pair>> lookupTbl = new HashMap<Integer, ArrayList<Pair>>();
        for (int i = 0; i < num.length - 1; i++) {
            for (int j = i + 1; j < num.length; j++) {
                int key = num[i] + num[j];
                if (!lookupTbl.containsKey(key))
                    lookupTbl.put(key, new ArrayList<Pair>());
                lookupTbl.get(key).add(new Pair(i, j));
            }
        }

        HashSet<ArrayList<Integer>> resultSet = new HashSet<ArrayList<Integer>>();
        for (Integer key : lookupTbl.keySet()) {
            int tgtKey = target - key;
            if (!lookupTbl.containsKey(tgtKey))
                continue;

            for (Pair pair1 : lookupTbl.get(key)) {
                for (Pair pair2 : lookupTbl.get(tgtKey)) {
                    if (pair1.a == pair2.a || pair1.a == pair2.b || pair1.b == pair2.a || pair1.b == pair2.b)
                        continue;
                    Integer[] r = { num[pair1.a], num[pair1.b], num[pair2.a], num[pair2.b] };
                    Arrays.sort(r);
                    resultSet.add(new ArrayList<Integer>(Arrays.asList(r)));
                }
            }
        }
        return new ArrayList<List<Integer>>(resultSet);
    }
}
