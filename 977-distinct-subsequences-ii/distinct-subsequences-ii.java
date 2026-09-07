class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        int[] ends = new int[26];
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            long total = 0;
            for (int count : ends) {
                total = (total + count) % MOD;
            }
            ends[idx] = (int) ((total + 1) % MOD);
        }
        int ans = 0;
        for (int count : ends) {
            ans = (ans + count) % MOD;
        }
        return ans;
    }
}