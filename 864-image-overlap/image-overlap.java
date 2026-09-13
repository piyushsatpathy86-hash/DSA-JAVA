class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> onesA = new ArrayList<>();
        List<int[]> onesB = new ArrayList<>();

        // Collect coordinates of 1s in each image
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) onesA.add(new int[]{i, j});
                if (img2[i][j] == 1) onesB.add(new int[]{i, j});
            }
        }

        // Count how often each (dx, dy) shift vector occurs between a 1 in A and a 1 in B
        Map<String, Integer> shiftCount = new HashMap<>();
        int maxOverlap = 0;

        for (int[] a : onesA) {
            for (int[] b : onesB) {
                int dx = a[0] - b[0];
                int dy = a[1] - b[1];
                String key = dx + "," + dy;
                int count = shiftCount.getOrDefault(key, 0) + 1;
                shiftCount.put(key, count);
                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
    }
}