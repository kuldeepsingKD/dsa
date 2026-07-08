class Solution {
    int MOD = 1000000007;
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        int[] nonZeroCount = new int[n];
        long[] numberUpto = new long[n];
        long[] digitSumUpto = new long[n];
        long[] pow10 = new long[n+1];

        pow10[0] = 1;
        for(int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i-1] * 10) % MOD;
        }

        nonZeroCount[0] = (s.charAt(0) != '0') ? 1 : 0;

        for(int i = 1; i < n; i++) {
            int digit = s.charAt(i) - '0';
            nonZeroCount[i] = nonZeroCount[i-1] + ((digit != 0) ? 1 : 0);
        }

         numberUpto[0] = s.charAt(0) - '0';
        for(int i = 1; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if(digit != 0) {
                numberUpto[i] = ((numberUpto[i-1] * 10) + digit) % MOD;
            } else {
                numberUpto[i] = numberUpto[i-1];
            }
        }

        digitSumUpto[0] = s.charAt(0) - '0';
        for(int i=1; i < n; i++) {
            int digit = s.charAt(i) - '0';
             digitSumUpto[i] =  digitSumUpto[i-1] + digit;

        }

        int q = queries.length;
        int[] result = new int[q];
        for(int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            long sum = digitSumUpto[r] - ((l == 0) ? 0 : digitSumUpto[l-1]);

            long numBefore = (l == 0) ? 0 :  numberUpto[l-1];

            int k = nonZeroCount[r] - ((l == 0) ? 0 : nonZeroCount[l-1]);

            long x =  (numberUpto[r] - (numBefore*pow10[k] % MOD) + MOD) % MOD;  

            result[i] = (int)((x*sum) % MOD); 
       }

       return result;
 

    }
}