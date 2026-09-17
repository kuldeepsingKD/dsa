class Solution {
    long M  = 1000000007;
     private long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        
        long num = factorial(n);
        long den = (factorial(r) * factorial(n - r)) % M;
        
        // Modulo division ke liye Modular Inverse multiply karte hain
        return (num * modInverse(den, M)) % M;
    }

    private long factorial(int num) {
        long fact = 1;
        for (int i = 2; i <= num; i++) {
            fact = (fact * i) % M;
        }
        return fact;
    }

    // Fermat's Little Theorem: inverse of a is a^(m-2) % m
    private long modInverse(long a, long m) {
        return power(a, m - 2, m);
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
    public int countVisiblePeople(int n, int pos, int k) {
        if(k > n-1) return 0;

        long ans = (2* nCr(n-1,k)) % M;

        return (int) ans;
    }
}