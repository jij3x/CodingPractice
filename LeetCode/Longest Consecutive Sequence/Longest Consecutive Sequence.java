public class Solution {
    public int longestConsecutive(int[] num) {
        HashSet<Integer> memo = new HashSet<Integer>();
        for (int n : num)
            memo.add(n);

        int max = 0;
        for (int n : num) {
            if (memo.contains(n)) {
                int cnt = 0;
                for (int more = n; memo.contains(more); cnt++)
                    memo.remove(more++);
                for (int less = n - 1; memo.contains(less); cnt++)
                    memo.remove(less--);
                max = Math.max(max, cnt);
            }
        }
        return max;
    }
}
