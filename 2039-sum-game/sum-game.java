class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;
        int sumL = 0, sumR = 0, cntL = 0, cntR = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') cntL++;
            else sumL += num.charAt(i) - '0';
        }
        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') cntR++;
            else sumR += num.charAt(i) - '0';
        }

        int diff = sumL - sumR;
        int totalCnt = cntL + cntR;

        if (totalCnt % 2 == 1) {
            return true; // Alice always wins with odd number of '?'
        }

        return diff != 9 * (cntR - cntL) / 2;
    }
}