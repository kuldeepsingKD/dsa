class Solution {
    private int solve(int i, int j, int[] nums) {
        if(i > j) return 0;
        if(i == j) return nums[i];

        int takes_i = nums[i] + Math.min(solve(i+2, j, nums), solve(i+1, j-1, nums));
        int takes_j = nums[j] + Math.min(solve(i+1, j-1, nums), solve(i, j-2, nums));

        return Math.max(takes_i, takes_j);
    }
    public boolean predictTheWinner(int[] nums) {
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