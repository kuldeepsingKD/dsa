class Solution {
    int[][] t = new int[23][23];
    private int solve(int i, int j, int[] nums) {
        if(i > j) return 0;
        if(i == j) return nums[i];

          if (t[i][j] != -1)
            return t[i][j];

        int takes_i = nums[i] + Math.min(solve(i+2, j, nums), solve(i+1, j-1, nums));
        int takes_j = nums[j] + Math.min(solve(i+1, j-1, nums), solve(i, j-2, nums));

        return t[i][j] = Math.max(takes_i, takes_j);
    }
    public boolean predictTheWinner(int[] nums) {
         for (int[] row : t)
            Arrays.fill(row, -1);
        int n = nums.length;
        int totalScore = 0;
        for(int num : nums) {
            totalScore += num;
        }

        int player1Score = solve(0, n-1, nums);
        int player2Score = totalScore - player1Score;


        return player1Score >= player2Score;
    }
}