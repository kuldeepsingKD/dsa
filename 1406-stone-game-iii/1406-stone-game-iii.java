class Solution {
      int n;
    Integer[] dp;

    private int solve(int[] stoneValue, int i) {
        if (i >= n) return 0;

        if (dp[i] != null) return dp[i];

        int take = 0;
        int ans = Integer.MIN_VALUE;

        for (int k = 0; k < 3 && i + k < n; k++) {
            take += stoneValue[i + k];
            ans = Math.max(ans, take - solve(stoneValue, i + k + 1));
        }

        return dp[i] = ans;
    }
    public String stoneGameIII(int[] stoneValue) {
          n = stoneValue.length;
        dp = new Integer[n];

        int diff = solve(stoneValue, 0);

        if (diff > 0) return "Alice";
        if (diff < 0) return "Bob";
        return "Tie";
    }
}