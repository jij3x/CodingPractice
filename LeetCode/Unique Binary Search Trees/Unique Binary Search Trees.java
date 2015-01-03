public class Solution {
    public int numTrees(int n) {
        if (n == 0)
            return 1;

        int total = 0;
        for (int i = 1; i <= n; i++)
            total += numTrees(i - 1) * numTrees(n - i);
        return total;
    }
}
