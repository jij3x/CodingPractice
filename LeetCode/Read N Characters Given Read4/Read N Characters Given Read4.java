/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int total = 0;
        char[] buf4 = new char[4];
        for (int last = 4; total < n && last == 4; total += last) {
            last = read4(buf4);
            System.arraycopy(buf4, 0, buf, total, last);
        }
        return Math.min(total, n);
    }
}