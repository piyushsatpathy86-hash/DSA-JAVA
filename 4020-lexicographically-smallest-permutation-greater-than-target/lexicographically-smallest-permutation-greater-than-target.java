import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try prefix lengths from longest (n - 1) down to 0
        for (int i = n - 1; i >= 0; i--) {
            int[] tempCount = new int[26];
            System.arraycopy(count, 0, tempCount, 0, 26);
            
            // Check if prefix target[0 ... i-1] can be formed
            boolean validPrefix = true;
            for (int j = 0; j < i; j++) {
                int charIdx = target.charAt(j) - 'a';
                if (--tempCount[charIdx] < 0) {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            // Pick the smallest available character strictly greater than target[i]
            int targetChar = target.charAt(i) - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (tempCount[c] > 0) {
                    tempCount[c]--;

                    StringBuilder sb = new StringBuilder();
                    sb.append(target, 0, i);
                    sb.append((char) ('a' + c));

                    // Fill remaining characters in ascending order
                    for (int k = 0; k < 26; k++) {
                        while (tempCount[k] > 0) {
                            sb.append((char) ('a' + k));
                            tempCount[k]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}