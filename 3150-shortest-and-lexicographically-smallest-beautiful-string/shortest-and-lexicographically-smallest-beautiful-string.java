class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0, onesCount = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                onesCount++;
            }

            // Shrink from left while window has more than k ones,
            // or while shrinking further still keeps exactly k ones
            while (onesCount > k || (onesCount == k && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1') {
                    onesCount--;
                }
                left++;
            }

            if (onesCount == k) {
                int len = right - left + 1;
                String candidate = s.substring(left, right + 1);

                if (len < minLen) {
                    minLen = len;
                    result = candidate;
                } else if (len == minLen && candidate.compareTo(result) < 0) {
                    result = candidate;
                }
            }
        }

        return result;
    }
}