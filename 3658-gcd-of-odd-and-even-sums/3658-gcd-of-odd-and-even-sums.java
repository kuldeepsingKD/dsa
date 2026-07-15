class Solution {
    private int gcd(int a, int b) {
        while(b != 0) {
            int temp = a%b;
            a = b;
            b = temp;

        }

        return Math.abs(a);
    }
    public int gcdOfOddEvenSums(int n) {
        int sumOdd = n * (2*1 + (n-1) * 2) / 2;
        int sumEven = n * (2*2 + (n-1) * 2) / 2;

        return gcd(sumOdd, sumEven);
    }
}