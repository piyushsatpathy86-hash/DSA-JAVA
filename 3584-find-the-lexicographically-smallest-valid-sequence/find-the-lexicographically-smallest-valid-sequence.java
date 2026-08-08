class Solution {
    public int[] validSequence(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();

        int[] suffixMatch = new int[n1 + 1];
        int j = n2;
        suffixMatch[n1] = 0;
        for (int i = n1 - 1; i >= 0; i--) {
            if (j > 0 && word1.charAt(i) == word2.charAt(j - 1)) j--;
            suffixMatch[i] = n2 - j;
        }

        int[] ans = new int[n2];
        int idx = 0;
        int t = 0;
        boolean used = false;

        while (idx < n1 && t < n2) {
            if (word1.charAt(idx) == word2.charAt(t)) {
                ans[t] = idx;
                idx++; t++;
            } else if (!used && suffixMatch[idx + 1] >= n2 - t - 1) {
                ans[t] = idx;
                used = true;
                idx++; t++;
            } else {
                idx++;
            }
        }

        return t < n2 ? new int[0] : ans;
    }
}