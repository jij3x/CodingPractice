public class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] memo = new int[256];
        for (char c : s.toCharArray())
            memo[c]++;

        int odds = 0;
        for (int e : memo)
            odds += e % 2;
        return s.length() % 2 == odds;
    }
}