interface NestedInteger {
    // Returns true if this NestedInteger holds a single integer, rather than a
    // nested list
    public boolean isInteger();

    // Returns the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Returns null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Returns the nested list that this NestedInteger holds, if it holds a
    // nested list
    // Returns null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class Solution {
    public int weightedSum(NestedInteger n) {
        if (n == null)
            return 0;
        if (n.isInteger())
            return n.getInteger();

        return doWeightedSum(n, 0);
    }

    private int doWeightedSum(NestedInteger n, int level) {
        if (n.isInteger())
            return n.getInteger() * level;

        int sum = 0;
        for (NestedInteger e : n.getList()) {
            sum += doWeightedSum(e, level + 1);
        }
        return sum;
    }
}
