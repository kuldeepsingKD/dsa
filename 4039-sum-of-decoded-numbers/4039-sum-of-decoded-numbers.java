class Solution {
      private long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod; // Handle cases where base >= mod
        
        while (exp > 0) {
            // If exp is odd, multiply base with result
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            // exp must be even now, square the base and halve the exponent
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
   
 
    public int sumDecoded(long[] nums) {
      long totalSum = 0;
        long MOD = 1_000_000_007L;

        for (long num : nums) {
            // Step 1: Extract width and d
            int width = (int) (num % 10);
            long d = num / 10;

            // Step 2: Slice strings to get x and y
            String dStr = String.valueOf(d);
            String xStr = dStr.substring(0, width);
            String yStr = dStr.substring(width);

            long x = Long.parseLong(xStr);
            long y = Long.parseLong(yStr);

            // Step 3: Compute (x^y) % MOD using binary exponentiation
            long decodedValue = power(x, y, MOD);

            // Step 4: Accumulate the sum
            totalSum = (totalSum + decodedValue) % MOD;
        }

        return (int) totalSum;
    }
}