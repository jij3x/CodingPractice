public class Solution {
    public boolean isAnagram(String s, String t) {
        int[] cnt = new int[256];
        for (int i = 0; i < s.length(); i++)
            cnt[s.charAt(i)]++;
        for (int i = 0; i < t.length(); i++) {
            if (--cnt[t.charAt(i)] < 0)
                return false;
        }
        return s.length() == t.length();
    }
}

class Solution2 {
    public boolean isAnagram(String s, String t) {
        char[] arr_s = s.toCharArray(), arr_t = t.toCharArray();
        Arrays.sort(arr_s);
        Arrays.sort(arr_t);
        return (new String(arr_s)).equals(new String(arr_t));
    }
}