public class Solution {
	public boolean isPalindrome(String s) {
		s = s.toLowerCase();
		int head = 0;
		int tail = s.length() - 1;
		while (head < tail) {
			if ((Character.isLetter(s.charAt(head)) || Character.isDigit(s.charAt(head)))
					&& (Character.isLetter(s.charAt(tail)) || Character.isDigit(s.charAt(tail)))) {
				if (s.charAt(head) != s.charAt(tail))
					return false;
				head++;
				tail--;
			} else if (Character.isLetter(s.charAt(head)) || Character.isDigit(s.charAt(head))) {
				tail--;
			} else {
				head++;
			}
		}
		return true;
	}
}