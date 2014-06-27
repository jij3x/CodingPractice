public class Solution {
    public String multiply(String num1, String num2) {
        int[] buffer = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                buffer[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                buffer[i + j] += buffer[i + j + 1] / 10;
                buffer[i + j + 1] %= 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0, sp = 0; i < buffer.length; i++) {
            sp += buffer[i] == 0 ? 0 : 1;
            if (sp != 0)
                result.append((char) (buffer[i] + '0'));
        }
        return result.length() == 0 ? "0" : result.toString();
    }
}
