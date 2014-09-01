public class Solution {
    public int doubleSquare(int m) {
        int limit = (int) Math.sqrt(m / 2.0);
        int total = 0;
        for (int i = 0; i <= limit; i++) {
            int j = (int) Math.sqrt(m - i * i);
            if (j * j + i * i == m)
                total++;
        }
        return total;
    }

    public int doubleSquare2(int m) {
        int total = 0;
        for (int i = 0; i <= (int) Math.sqrt(m); i++) {
            for (int j = 0; j <= i; j++) {
                if (i * i + j * j == m)
                    total++;
                else if (i * i + j * j > m)
                    break;
            }
        }
        return total;
    }
}
