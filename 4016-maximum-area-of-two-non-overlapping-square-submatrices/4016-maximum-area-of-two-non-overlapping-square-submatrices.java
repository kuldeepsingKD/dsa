class Solution {
    public int maxArea(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }

        int m = mat.length;
        int n = mat[0].length;

        // dp[r][c] stores the max square side length with bottom-right corner at (r, c)
        int[][] dp = new int[m][n];
        
        int[] maxAbove = new int[m];
        int[] maxBelow = new int[m];
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        // 1. Compute bottom-right DP and accumulate prefix maximums (Above and Left)
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 1) {
                    int top = (r > 0) ? dp[r - 1][c] : 0;
                    int left = (c > 0) ? dp[r][c - 1] : 0;
                    int diag = (r > 0 && c > 0) ? dp[r - 1][c - 1] : 0;
                    dp[r][c] = Math.min(Math.min(top, left), diag) + 1;
                }
                maxAbove[r] = Math.max(maxAbove[r], dp[r][c]);
                maxLeft[c] = Math.max(maxLeft[c], dp[r][c]);
            }
        }

        // Forward propagation to create cumulative prefix maximums
        for (int r = 1; r < m; r++) maxAbove[r] = Math.max(maxAbove[r], maxAbove[r - 1]);
        for (int c = 1; c < n; c++) maxLeft[c] = Math.max(maxLeft[c], maxLeft[c - 1]);

        // Clear DP table to re-use it for top-left calculation
        for (int[] row : dp) Arrays.fill(row, 0);

        // 2. Compute top-left DP and accumulate suffix maximums (Below and Right)
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (mat[r][c] == 1) {
                    int bottom = (r < m - 1) ? dp[r + 1][c] : 0;
                    int right = (c < n - 1) ? dp[r][c + 1] : 0;
                    int diag = (r < m - 1 && c < n - 1) ? dp[r + 1][c + 1] : 0;
                    dp[r][c] = Math.min(Math.min(bottom, right), diag) + 1;
                }
                maxBelow[r] = Math.max(maxBelow[r], dp[r][c]);
                maxRight[c] = Math.max(maxRight[c], dp[r][c]);
            }
        }

        // Backward propagation to create cumulative suffix maximums
        for (int r = m - 2; r >= 0; r--) maxBelow[r] = Math.max(maxBelow[r], maxBelow[r + 1]);
        for (int c = n - 2; c >= 0; c--) maxRight[c] = Math.max(maxRight[c], maxRight[c + 1]);

        // 3. Find the maximum possible uniform side length k across any line split
        int maxK = 0;

        // Try every possible horizontal dividing line between row r and r+1
        for (int r = 0; r < m - 1; r++) {
            maxK = Math.max(maxK, Math.min(maxAbove[r], maxBelow[r + 1]));
        }

        // Try every possible vertical dividing line between col c and c+1
        for (int c = 0; c < n - 1; c++) {
            maxK = Math.max(maxK, Math.min(maxLeft[c], maxRight[c + 1]));
        }

        return maxK * maxK;
    }
}