public class Solution {
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= num.length - 4; i++) {
            if (i > 0 && num[i] == num[i - 1])
                continue;

            for (int j = i + 1; j <= num.length - 3; j++) {
                if (j > i + 1 && num[j] == num[j - 1])
                    continue;

                int start = j + 1, end = num.length - 1;
                while (start < end) {
                    if (num[i] + num[j] + num[start] + num[end] == target) {
                        Integer[] r = { num[i], num[j], num[start++], num[end--] };
                        result.add(new ArrayList<Integer>(Arrays.asList(r)));

                        while (start < end && num[start] == num[start - 1])
                            start++;
                        while (start < end && num[end] == num[end + 1])
                            end--;
                    } else if (num[i] + num[j] + num[start] + num[end] > target) {
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

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Pair))
                return false;
            return this.a == ((Pair) other).a && this.b == ((Pair) other).b;
        }

        @Override
        public int hashCode() {
            return (23 * 31 + this.a) * 31 + this.b;
        }
    }

    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        HashMap<Integer, HashSet<Pair>> lookupTbl = new HashMap<Integer, HashSet<Pair>>();
        for (int i = 0; i < num.length - 1; i++) {
            for (int j = i + 1; j < num.length; j++) {
                int key = num[i] + num[j];
                HashSet<Pair> pairSet = null;
                if (lookupTbl.containsKey(key)) {
                    pairSet = lookupTbl.get(key);
                } else {
                    pairSet = new HashSet<Pair>();
                    lookupTbl.put(key, pairSet);
                }
                pairSet.add(new Pair(i, j));
            }
        }

        HashSet<ArrayList<Integer>> resultSet = new HashSet<ArrayList<Integer>>();
        for (Integer key : lookupTbl.keySet()) {
            int tgtKey = target - key;
            if (!lookupTbl.containsKey(tgtKey) || lookupTbl.get(tgtKey).size() == 0)
                continue;

            HashSet<Pair> pairSet1 = lookupTbl.get(key);
            HashSet<Pair> pairSet2 = lookupTbl.get(tgtKey);
            for (Iterator<Pair> i1 = pairSet1.iterator(); i1.hasNext();) {
                Pair pair1 = i1.next();
                for (Iterator<Pair> i2 = pairSet2.iterator(); i2.hasNext();) {
                    Pair pair2 = i2.next();
                    if (pair1.a == pair2.a || pair1.a == pair2.b || pair1.b == pair2.a || pair1.b == pair2.b)
                        continue;
                    Integer[] r = { num[pair1.a], num[pair1.b], num[pair2.a], num[pair2.b] };
                    Arrays.sort(r);
                    resultSet.add(new ArrayList<Integer>(Arrays.asList(r)));
                }
            }

            pairSet1.clear();
            pairSet2.clear();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (Iterator<ArrayList<Integer>> it = resultSet.iterator(); it.hasNext();) {
            result.add(it.next());
        }
        return result;
    }
}
