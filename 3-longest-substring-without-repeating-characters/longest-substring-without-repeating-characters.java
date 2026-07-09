class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] index = new int[128];
        Arrays.fill(index, -1);
        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (index[c] >= start) {
                start = index[c] + 1;
            }
            index[c] = i;
            max = Math.max(max, i - start + 1);
        }
        return max;
    }
}