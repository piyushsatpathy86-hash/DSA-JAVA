class Solution {
    public int totalNQueens(int n) {
        boolean[] usedCols = new boolean[n];
        boolean[] usedDiag1 = new boolean[2 * n]; // row - col + n
        boolean[] usedDiag2 = new boolean[2 * n]; // row + col

        return backtrack(0, n, usedCols, usedDiag1, usedDiag2);
    }

    private int backtrack(int row, int n, boolean[] usedCols,
                           boolean[] usedDiag1, boolean[] usedDiag2) {
        if (row == n) return 1; // found one valid full placement

        int count = 0;

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;
            int d2 = row + col;

            if (usedCols[col] || usedDiag1[d1] || usedDiag2[d2]) continue;

            // Place queen
            usedCols[col] = true;
            usedDiag1[d1] = true;
            usedDiag2[d2] = true;

            count += backtrack(row + 1, n, usedCols, usedDiag1, usedDiag2);

            // Remove queen (backtrack)
            usedCols[col] = false;
            usedDiag1[d1] = false;
            usedDiag2[d2] = false;
        }

        return count;
    }
}