public class Solution {
    public void setZeroes(int[][] matrix) {
        boolean clear1stRow = false, clear1stColumn = false;
        for (int y = 0; y < matrix.length; y++) {
            if (matrix[y][0] == 0)
                clear1stColumn = true;
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
            for (int x = 1; x < matrix[0].length; x++) {
                if (matrix[y][0] == 0 || matrix[0][x] == 0)
                    matrix[y][x] = 0;
            }
        }

        if (clear1stRow) {
            for (int x = 0; x < matrix[0].length; x++)
                matrix[0][x] = 0;
        }
        if (clear1stColumn) {
            for (int y = 0; y < matrix.length; y++)
                matrix[y][0] = 0;
        }
    }
}

class Solution2 {
    public void setZeroes(int[][] matrix) {
        boolean clearFirstColumn = false;
        for (int y = 0; y < matrix.length; y++) {
            if (matrix[y][0] == 0)
                clearFirstColumn = true;
            for (int x = 1; x < matrix[0].length; x++) {
                if (matrix[y][x] == 0)
                    matrix[y][0] = matrix[0][x] = 0;
            }
        }

        for (int y = matrix.length - 1; y >= 0; y--) {
            for (int x = 1; x < matrix[0].length; x++) {
                if (matrix[y][0] == 0 || matrix[0][x] == 0)
                    matrix[y][x] = 0;
            }
            if (clearFirstColumn)
                matrix[y][0] = 0;
        }
    }
}
