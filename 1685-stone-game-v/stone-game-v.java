class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        Integer[][] memo = new Integer[n][n];
        return solve(0, n - 1, prefix, memo);
    }

    private int solve(int i, int j, int[] prefix, Integer[][] memo) {
        if (i == j) return 0; // single stone left, no more rounds
        if (memo[i][j] != null) return memo[i][j];

        int best = 0;
        for (int k = i; k < j; k++) {
            int left = prefix[k + 1] - prefix[i];       // sum of stones[i..k]
            int right = prefix[j + 1] - prefix[k + 1];   // sum of stones[k+1..j]

            if (left < right) {
                // right row is discarded, left row survives
                best = Math.max(best, left + solve(i, k, prefix, memo));
            } else if (left > right) {
                // left row is discarded, right row survives
                best = Math.max(best, right + solve(k + 1, j, prefix, memo));
            } else {
                // tie: Alice picks whichever surviving row is better for her
                best = Math.max(best, left + Math.max(
                        solve(i, k, prefix, memo),
                        solve(k + 1, j, prefix, memo)));
            }
        }
        return memo[i][j] = best;
    }
}