public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int carry = 0, i = a.length() - 1, j = b.length() - 1; carry > 0 || i >= 0 || j >= 0; i--, j--) {
            carry += (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0);
            result.insert(0, carry % 2);
            carry /= 2;
        }
        return result.toString();
    }
}
