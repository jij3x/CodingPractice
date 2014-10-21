public class Solution {
    public int numTrees(int n) {
        if (n == 0)
            return 1;

        int total = 0;
        for (int i = 0; i < n; i++)
            total += numTrees(i) * numTrees(n - 1 - i);
        return total;
    }
}
