class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col < 2 || col > 9) continue;
            int bit = col - 2;
            int mask = rowMasks.getOrDefault(row, 0);
            mask |= (1 << bit);
            rowMasks.put(row, mask);
        }

        int left   = 0b00001111;
        int middle = 0b00111100;
        int right  = 0b11110000;

        int totalFamilies = 2 * (n - rowMasks.size());

        for (int mask : rowMasks.values()) {
            if ((mask & left) == 0) {
                totalFamilies += 1;
                mask |= left;
            }
            if ((mask & right) == 0) {
                totalFamilies += 1;
            } else if ((mask & middle) == 0) {
                totalFamilies += 1;
            }
        }

        return totalFamilies;
    }
}