class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long lo = 1, hi = (long) Arrays.stream(coins).min().getAsInt() * k;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (countLE(coins, mid) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    private long countLE(int[] coins, long x) {
        int n = coins.length;
        long count = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = Integer.bitCount(mask);
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lcm = lcmOf(lcm, coins[i]);
                    if (lcm > x) { overflow = true; break; }
                }
            }
            if (overflow) continue;

            if (bits % 2 == 1) {
                count += x / lcm;
            } else {
                count -= x / lcm;
            }
        }
        return count;
    }
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    private long lcmOf(long a, long b) {
        return a / gcd(a, b) * b;
    }
}