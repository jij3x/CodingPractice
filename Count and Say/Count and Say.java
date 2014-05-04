public class Solution {
    public String countAndSay(int n) {
        StringBuilder result = new StringBuilder("1");
        for (int i = 1; i < n; i++) {
            StringBuilder r = new StringBuilder();
            for (int j = 0, count = 1; j < result.length(); j++, count++) {
                if (j == result.length() - 1 || result.charAt(j) != result.charAt(j + 1)) {
                    r.append(count).append(result.charAt(j));
                    count = 0;
                }
            }
            result = r;
        }

        return result.toString();
    }
}

class Solution2 {
    public String countAndSay(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {
            result = convert(result);
        }
        return result;
    }

    private String convert(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0, j = 0, count = 0; i < input.length();) {
            for (j = i, count = 0; j < input.length(); j++) {
                if (input.charAt(j) == input.charAt(i))
                    count++;
                else
                    break;
            }
            output.append(count).append(input.charAt(i));
            i = j;
        }
        return output.toString();
    }
}
