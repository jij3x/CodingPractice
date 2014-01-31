public class Solution {
	class Coordinate {
		int x;
		int y;

		Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public void solve(char[][] board) {
		if (board.length == 0 || board[0].length == 0)
			return;

		Queue<Coordinate> workingQue = new LinkedList<Coordinate>();
		for (int y = 0; y < board.length; y++) {
			if (board[y][0] == 'O') {
				board[y][0] = '$';
				workingQue.add(new Coordinate(0, y));
			}
			if (board[y][board[0].length - 1] == 'O') {
				board[y][board[0].length - 1] = '$';
				workingQue.add(new Coordinate(board[0].length - 1, y));
			}
		}

		for (int x = 0; x < board[0].length; x++) {
			if (board[0][x] == 'O') {
				board[0][x] = '$';
				workingQue.add(new Coordinate(x, 0));
			}
			if (board[board.length - 1][x] == 'O') {
				board[board.length - 1][x] = '$';
				workingQue.add(new Coordinate(x, board.length - 1));
			}
		}

		while (!workingQue.isEmpty()) {
			Coordinate c = workingQue.poll();

			if (c.x - 1 > 0 && board[c.y][c.x - 1] == 'O') {
				board[c.y][c.x - 1] = '$';
				workingQue.add(new Coordinate(c.x - 1, c.y));
			}

			if (c.x + 1 < board[0].length - 1 && board[c.y][c.x + 1] == 'O') {
				board[c.y][c.x + 1] = '$';
				workingQue.add(new Coordinate(c.x + 1, c.y));
			}

			if (c.y - 1 > 0 && board[c.y - 1][c.x] == 'O') {
				board[c.y - 1][c.x] = '$';
				workingQue.add(new Coordinate(c.x, c.y - 1));
			}

			if (c.y + 1 < board.length - 1 && board[c.y + 1][c.x] == 'O') {
				board[c.y + 1][c.x] = '$';
				workingQue.add(new Coordinate(c.x, c.y + 1));
			}
		}

		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[0].length; x++) {
				board[y][x] = board[y][x] == 'O' ? 'X' : board[y][x];
				board[y][x] = board[y][x] == '$' ? 'O' : board[y][x];
			}
		}
	}
}