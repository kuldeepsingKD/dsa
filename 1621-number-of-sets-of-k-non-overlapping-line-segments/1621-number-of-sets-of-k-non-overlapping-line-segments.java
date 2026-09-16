class Solution {
    int M = 1000000007;
    int[][][] t;
    public int solve(int n, int k, int i, int status) {
        // Base case: If we successfully formed all k segments
        if (k == 0) return 1;
        // Base case: Out of bounds
        if (i >= n) return 0;
        
        if (t[k][i][status] != -1) {
            return t[k][i][status];
        }

        long ans = 0;

        if (status == 0) {
            // Option 1: Skip the current point completely
            long skip = solve(n, k, i + 1, 0);
            // Option 2: Start a new segment at the current point
            long start = solve(n, k, i + 1, 1);
            ans = (skip + start) % M;
        } else {
            // Option 1: Continue the current segment to the next point
            long keepGoing = solve(n, k, i + 1, 1);
            // Option 2: End the current segment at this point
            // Since segments can share endpoints, the next segment can start right here (i, status 0)
            long endHere = solve(n, k - 1, i, 0);
            ans = (keepGoing + endHere) % M;
        }

        return t[k][i][status] = (int) ans;
    }
    public int numberOfSets(int n, int k) {

         t = new int[k + 1][n + 1][2];
        for (int[][] matrix : t) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }
        return solve(n, k, 0, 0) % M;
    }
}