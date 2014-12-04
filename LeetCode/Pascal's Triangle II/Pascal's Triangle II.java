public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> prev = new ArrayList<Integer>(), curr;
        prev.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            curr = new ArrayList<Integer>();
            curr.add(1);
            for (int j = 1; j < i; j++)
                curr.add(prev.get(j - 1) + prev.get(j));
            curr.add(1);
            prev = curr;
        }
        return prev;
    }
}

class Solution2 {
    public List<Integer> getRow(int rowIndex) {
        Integer[] result = new Integer[rowIndex + 1];
        Arrays.fill(result, 1);

        for (int i = 2; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--)
                result[j] += result[j - 1];
        }
        return new ArrayList<Integer>(Arrays.asList(result));
    }
}
