public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        if (n == 0) {
            result.add(0);
            return result;
        }

        result.addAll(grayCode(n - 1));
        for (int i = result.size() - 1; i >= 0; i--)
            result.add((1 << (n - 1)) + result.get(i));

        return result;
    }
}

class Solution2 {
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < (1 << n); i++)
            result.add((i >> 1) ^ i);

        return result;
    }
}
