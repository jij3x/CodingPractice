public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;

        int div = 10;
        while (x / div > 9) {
            div *= 10;
        }
        
        while (x > 9) {
            int head = x / div;
            int tail = x % 10;
            if (head != tail)
                return false;
                
            x %= div;
            x /= 10;
            div /= 100;
        }
        return true;
    }
}