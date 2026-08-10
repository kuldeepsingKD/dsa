class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n+1];

        dp[0] = false;

        for(int stones = 1; stones <= n; stones++) {
            for(int i=1; i*i <= stones; i++){
                int sq = i*i;

                if(!dp[stones-sq]){
                    dp[stones] = true;
                    break;
                }
            }

        }
        return dp[n];

         

    }
}