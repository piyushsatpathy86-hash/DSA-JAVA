class Solution {
    public int reverse(int a) {
        long result = 0;
        while (a != 0) {
            int digit = a % 10;
            a /= 10;
            result = result * 10 + digit;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
        }   
        return (int) result;
    }
}