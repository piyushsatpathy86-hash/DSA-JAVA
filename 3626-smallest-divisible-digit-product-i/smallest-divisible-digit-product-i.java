class Solution {
    public int smallestNumber(int n, int t) {
        int num = n;
        while (true) {
            long product = 1;
            int temp = num;
            while (temp > 0) {
                product *= (temp % 10);
                temp /= 10;
            }
            if (product % t == 0) {
                return num;
            }
            num++;
        }
    }
}