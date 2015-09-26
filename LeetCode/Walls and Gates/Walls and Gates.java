public class Solution {
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> q = new LinkedList<int[]>();
        for (int y = 0; rooms.length > 0 && y < rooms.length; y++) {
            for (int x = 0; x < rooms[0].length; x++) {
                if (rooms[y][x] == 0)
                    q.add(new int[] { y, x });
            }
        }

        while (!q.isEmpty()) {
            int[] c = q.poll();
            int[][] dt = { { c[0] + 1, c[1] }, { c[0] - 1, c[1] }, { c[0], c[1] + 1 }, { c[0], c[1] - 1 } };
            for (int[] pos : dt) {
                if (pos[0] >= 0 && pos[0] < rooms.length && pos[1] >= 0 && pos[1] < rooms[0].length
                        && rooms[pos[0]][pos[1]] == Integer.MAX_VALUE) {
                    rooms[pos[0]][pos[1]] = rooms[c[0]][c[1]] + 1;
                    q.add(pos);
                }
            }
        }
    }
}
