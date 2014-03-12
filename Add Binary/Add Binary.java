public class Solution {
	public String addBinary(String a, String b) {
		int ae = a.length() - 1, be = b.length() - 1, carry = 0;
		StringBuilder result = new StringBuilder("");
		while (ae >= 0 || be >= 0 || carry > 0) {
			carry += (ae >= 0 ? a.charAt(ae) - '0' : 0) + (be >= 0 ? b.charAt(be) - '0' : 0);
			result.insert(0, (char) ((carry % 2) + '0'));
			carry /= 2;

			ae = ae >= 0 ? ae - 1 : ae;
			be = be >= 0 ? be - 1 : be;
		}
		return result.toString();
	}
}