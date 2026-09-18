class Solution {
    public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {
         
        
        // Step 1: Row shifts apply karne ke liye temporary matrix
        int[][] afterRowShift = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Left shift ke baad naya column index calculate karein
                int newJ = (j - rowShift[i] % n + n) % n;
                afterRowShift[i][newJ] = grid[i][j];
            }
        }
        
        // Step 2: Column shifts apply karne ke liye final matrix
        int[][] finalGrid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Upward shift ke baad naya row index calculate karein
                int newI = (i - colShift[j] % n + n) % n;
                finalGrid[newI][j] = afterRowShift[i][j];
            }
        }
        
        return finalGrid;
    }
}