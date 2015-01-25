public class Solution {
    public int longestConsecutive(int[] num) {
        HashSet<Integer> memo = new HashSet<Integer>();
        for (int n : num)
            memo.add(n);

        int max = 0;
        for (int n : num) {
            if (!memo.contains(n))
                continue;

            int cnt = 0;
            for (int more = n; memo.contains(more); memo.remove(more++))
                cnt++;
            for (int less = n - 1; memo.contains(less); memo.remove(less--))
                cnt++;
            max = Math.max(max, cnt);
        }
        return max;
    }
}
