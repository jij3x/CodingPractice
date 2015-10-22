public class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> rst = new ArrayList<String>();
        for (String s : dfs(n)) {
            if (s == "0" || s.charAt(0) != '0')
                rst.add(s);
        }
        return rst;
    }

    private List<String> dfs(int n) {
        ArrayList<String> rst = new ArrayList<String>();
        if (n == 0) {
            rst.add("");
        } else if (n == 1) {
            rst.addAll(Arrays.asList(new String[] { "0", "1", "8" }));
        } else {
            for (String row : dfs(n - 2)) {
                rst.add("0" + row + "0");
                rst.add("1" + row + "1");
                rst.add("8" + row + "8");
                rst.add("6" + row + "9");
                rst.add("9" + row + "6");
            }
        }
        return rst;
    }
}