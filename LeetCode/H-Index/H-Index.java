public class Solution {
    public int hIndex(int[] citations) {
        int[] cnt = new int[citations.length + 1];
        for (int i = 0; i < citations.length; i++) {
            cnt[Math.min(citations.length, citations[i])]++;
        }
        for (int i = citations.length, acc = 0; i >= 1; i--) {
            if ((acc += cnt[i]) >= i)
                return i;
        }
        return 0;
    }
}