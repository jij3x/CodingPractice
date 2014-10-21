public class Solution {
    public int longestConsecutive(int[] num) {
        HashSet<Integer> tbl = new HashSet<Integer>();
        for (int i : num)
            tbl.add(i);

        int maxLen = 0;
        for (int i : num) {
            if (!tbl.contains(i))
                continue;

            int len = 1, less = i, more = i;
            tbl.remove(i);
            while (tbl.contains(--less)) {
                tbl.remove(less);
                len++;
            }
            while (tbl.contains(++more)) {
                tbl.remove(more);
                len++;
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}
