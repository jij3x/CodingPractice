public class Solution {
    public String multiply(String num1, String num2) {
        int[] buffer = new int[num1.length() + num2.length()];
        for (int i = num2.length() - 1; i >= 0; i--) {
            for (int j = num1.length() - 1; j >= 0; j--) {
                buffer[i + j + 1] += (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                buffer[i + j] += buffer[i + j + 1] / 10;
                buffer[i + j + 1] %= 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0, noneZeros = 0; i < buffer.length; i++) {
            noneZeros += buffer[i] == 0 ? 0 : 1;
            result.append(noneZeros > 0 ? (char) (buffer[i] + '0') : "");
        }
        return result.length() == 0 ? "0" : result.toString();
    }
}
