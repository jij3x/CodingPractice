public class Solution {
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            List<Integer> result = new ArrayList<Integer>();
            result.add(0);
            return result;
        }

        List<Integer> result = grayCode(n - 1);
        for (int i = result.size() - 1; i >= 0; i--)
            result.add(result.get(i) + (1 << (n - 1)));

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
