public class Solution {
    public void setZeroes(int[][] matrix) {
        boolean clear1stRow = false, clear1stCol = false;
        for (int y = 0; y < matrix.length; y++) {
            if (matrix[y][0] == 0)
                clear1stCol = true;
        }
        for (int x = 0; x < matrix[0].length; x++) {
            if (matrix[0][x] == 0)
                clear1stRow = true;
        }

        for (int y = 1; y < matrix.length; y++) {
            for (int x = 1; x < matrix[0].length; x++) {
                if (matrix[y][x] == 0) {
                    matrix[y][0] = 0;
                    matrix[0][x] = 0;
                }
            }
        }

        for (int y = 1; y < matrix.length; y++) {
            if (matrix[y][0] == 0) {
                for (int x = 1; x < matrix[0].length; x++)
                    matrix[y][x] = 0;
            }
        }
        for (int x = 1; x < matrix[0].length; x++) {
            if (matrix[0][x] == 0) {
                for (int y = 1; y < matrix.length; y++)
                    matrix[y][x] = 0;
            }
        }

        if (clear1stRow) {
            for (int x = 0; x < matrix[0].length; x++)
                matrix[0][x] = 0;
        }
        if (clear1stCol) {
            for (int y = 0; y < matrix.length; y++)
                matrix[y][0] = 0;
        }
    }
}
