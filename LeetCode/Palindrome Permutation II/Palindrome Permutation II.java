public class Solution {
    public List<String> generatePalindromes(String s) {
        int[] memo = new int[256];
        for (char c : s.toCharArray())
            memo[c]++;

        String buf = "";
        String center = "";
        for (int odds = 0, c = 0; c < memo.length; c++) {
            if ((odds += memo[c] % 2) > 1)
                return new ArrayList<String>();
            if (memo[c] % 2 == 1)
                center = Character.toString((char) c);
            for (int i = 0; i < memo[c] / 2; i++)
                buf += (char) c;
        }

        List<String> temp = dfs(buf);
        ArrayList<String> result = new ArrayList<String>();
        for (String str : temp)
            result.add(str + center + (new StringBuilder(str)).reverse().toString());
        return result;
    }

    private List<String> dfs(String s) {
        ArrayList<String> result = new ArrayList<String>();
        if (s.isEmpty()) {
            result.add("");
            return result;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            String str = s.substring(0, i) + s.substring(i + 1);
            for (String subStr : dfs(str))
                result.add(c + subStr);
            while (i != s.length() - 1 && s.charAt(i) == s.charAt(i + 1))
                i++;
        }
        return result;
    }
}
