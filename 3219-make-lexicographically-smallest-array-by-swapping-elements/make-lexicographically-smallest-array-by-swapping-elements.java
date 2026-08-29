class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);

        int[] ans = new int[n];
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                j++;
            }
            List<Integer> group = new ArrayList<>();
            for (int k = i; k < j; k++) group.add(idx[k]);
            Collections.sort(group);

            int val = nums[idx[i]];
            for (int k = 0; k < group.size(); k++) {
                ans[group.get(k)] = nums[idx[i + k]];
            }
            i = j;
        }
        return ans;
    }
}