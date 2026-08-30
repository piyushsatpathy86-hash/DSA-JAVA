class Solution {
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int dp1 = 1, dp2 = 1, ans = 1;
        for (int i = 1; i < n; i++) {
            int ndp1 = 1, ndp2 = 1;
            if (nums1[i] >= nums1[i - 1]) ndp1 = Math.max(ndp1, dp1 + 1);
            if (nums1[i] >= nums2[i - 1]) ndp1 = Math.max(ndp1, dp2 + 1);
            if (nums2[i] >= nums1[i - 1]) ndp2 = Math.max(ndp2, dp1 + 1);
            if (nums2[i] >= nums2[i - 1]) ndp2 = Math.max(ndp2, dp2 + 1);
            dp1 = ndp1;
            dp2 = ndp2;
            ans = Math.max(ans, Math.max(dp1, dp2));
        }
        return ans;
    }
}