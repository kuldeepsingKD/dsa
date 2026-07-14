class Solution {
    private static final int MOD =1000000007;
    private static int[][][] memo;
    private static int maxVal;
    private int  solve(int i, int g1, int g2, int[] nums) {
        if(i == nums.length) {
            return (g1 == g2 && g1 > 0) ? 1 : 0;
        }

            if(memo[i][g1][g2] != -1) {
                return memo[i][g1][g2];
            }

            long total = solve(i+1, g1, g2, nums);

            int nextG1 = (g1 == 0) ? nums[i] : gcd(g1, nums[i]);
            total = (total + solve(i+1, nextG1, g2, nums))%MOD;

             int nextG2 = (g2 == 0) ? nums[i] : gcd(g2, nums[i]);
            total = (total + solve(i+1, g1, nextG2, nums))%MOD;

            return memo[i][g1][g2] = (int)total;
        
    }

    private int gcd(int a, int b) {
        while(b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a; 
    }
    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        maxVal = 0;
        for(int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        memo = new int[n][maxVal+1][maxVal+1];
        for(int[][] mat : memo) {
            for(int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }

        int result = solve(0, 0, 0, nums) ;
        return (result + MOD) % MOD;
    }
}