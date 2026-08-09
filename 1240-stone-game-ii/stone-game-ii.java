class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        int[][] memo = new int[n][n + 1];
        for (int[] row : memo) Arrays.fill(row, -1);

        return dp(0, 1, n, suffix, memo);
    }

    private int dp(int i, int m, int n, int[] suffix, int[][] memo) {
        if (i == n) return 0;
        if (i + 2 * m >= n) return suffix[i];  // take everything remaining

        if (memo[i][m] != -1) return memo[i][m];

        int best = 0;
        for (int x = 1; x <= 2 * m; x++) {
            if (i + x > n) break;
            int newM = Math.max(m, x);
            best = Math.max(best, suffix[i] - dp(i + x, newM, n, suffix, memo));
        }

        memo[i][m] = best;
        return best;
    }
}