class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";
        int left = 0, ones = 0;

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') ones++;

            while (ones > k || (ones == k && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1') ones--;
                left++;
            }

            if (ones == k) {
                String candidate = s.substring(left, right + 1);
                if (ans.isEmpty() || candidate.length() < ans.length() ||
                    (candidate.length() == ans.length() && candidate.compareTo(ans) < 0)) {
                    ans = candidate;
                }
            }
        }

        return ans;
    }
}