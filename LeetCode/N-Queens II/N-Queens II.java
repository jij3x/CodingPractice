public class Solution {
    private int total;

    public int totalNQueens(Integer n) {
        total = 0;
        doTotal(0, new int[n]);
        return total;
    }

    private void doTotal(int level, int[] board) {
        if (level == board.length) {
            total++;
            return;
        }

        nextPos: for (int i = 0; i < board.length; i++) {
            board[level] = i;
            for (int j = level - 1; j >= 0; j--) {
                if (board[j] == i || board[j] == i - (level - j) || board[j] == i + (level - j))
                    continue nextPos;
            }
            doTotal(level + 1, board);
        }
    }
}

class Solution2 {
    int count, all;

    public int totalNQueens(int n) {
        count = 0;
        all = (1 << n) - 1;
        doTotalNQueens(0, 0, 0);
        return count;
    }

    private void doTotalNQueens(int ld, int row, int rd) {
        int poss, p;
        if (row == all) {
            count++;
        } else {
            poss = all & ~(ld | row | rd);
            while (poss != 0) {
                p = poss & (-poss); // get poss' right most '1'
                poss = poss - p;
                doTotalNQueens((ld | p) << 1, row | p, (rd | p) >> 1);
            }
        }
    }
}
