public class Solution {
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;

        Queue<Integer> qx = new LinkedList<Integer>(), qy = new LinkedList<Integer>();
        for (int x = 0; x < board[0].length; x++) {
            if (board[0][x] == 'O') {
                qy.add(0);
                qx.add(x);
                board[0][x] = '$';
            }
            if (board[board.length - 1][x] == 'O') {
                qy.add(board.length - 1);
                qx.add(x);
                board[board.length - 1][x] = '$';
            }
        }
        for (int y = 1; y < board.length - 1; y++) {
            if (board[y][0] == 'O') {
                qy.add(y);
                qx.add(0);
                board[y][0] = '$';
            }
            if (board[y][board[0].length - 1] == 'O') {
                qy.add(y);
                qx.add(board[0].length - 1);
                board[y][board[0].length - 1] = '$';
            }
        }

        while (!qy.isEmpty()) {
            int y = qy.poll(), x = qx.poll();
            int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 };
            for (int i = 0; i < 4; i++) {
                if (y + dy[i] < board.length && y + dy[i] >= 0 && x + dx[i] < board[0].length && x + dx[i] >= 0
                        && board[y + dy[i]][x + dx[i]] == 'O') {
                    qy.add(y + dy[i]);
                    qx.add(x + dx[i]);
                    board[y + dy[i]][x + dx[i]] = '$';
                }
            }
        }

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++)
                board[y][x] = board[y][x] == '$' ? 'O' : 'X';
        }
    }
}
