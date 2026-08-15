class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        int cnt0 = 0;
        int n = nums.length;

        for (int x : nums) {
            xor ^= x;
            if (x == 0) cnt0++;
        }

        if (xor != 0) return n;
        if (cnt0 == n) return 0;
        return n - 1;
    }
}