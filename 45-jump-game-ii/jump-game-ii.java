class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int jumps = 0;
        int currentEnd = 0;      // farthest index reachable with current number of jumps
        int farthest = 0;        // farthest index reachable by looking one jump ahead

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            // If we've reached the end of the range for the current jump count,
            // we must take another jump
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // Early exit: if we can already reach the end, stop
                if (currentEnd >= n - 1) break;
            }
        }

        return jumps;
    }
}