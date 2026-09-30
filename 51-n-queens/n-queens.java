class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        int[] queenCols = new int[n]; // queenCols[row] = column of queen placed in that row

        boolean[] usedCols = new boolean[n];
        boolean[] usedDiag1 = new boolean[2 * n]; // row - col + n (to avoid negative index)
        boolean[] usedDiag2 = new boolean[2 * n]; // row + col

        backtrack(0, n, queenCols, usedCols, usedDiag1, usedDiag2, results);
        return results;
    }

    private void backtrack(int row, int n, int[] queenCols, boolean[] usedCols,
                            boolean[] usedDiag1, boolean[] usedDiag2,
                            List<List<String>> results) {
        if (row == n) {
            results.add(buildBoard(queenCols, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;
            int d2 = row + col;

            if (usedCols[col] || usedDiag1[d1] || usedDiag2[d2]) continue;

            // Place queen
            queenCols[row] = col;
            usedCols[col] = true;
            usedDiag1[d1] = true;
            usedDiag2[d2] = true;

            backtrack(row + 1, n, queenCols, usedCols, usedDiag1, usedDiag2, results);

            // Remove queen (backtrack)
            usedCols[col] = false;
            usedDiag1[d1] = false;
            usedDiag2[d2] = false;
        }
    }

    private List<String> buildBoard(int[] queenCols, int n) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            char[] rowChars = new char[n];
            Arrays.fill(rowChars, '.');
            rowChars[queenCols[row]] = 'Q';
            board.add(new String(rowChars));
        }
        return board;
    }
}