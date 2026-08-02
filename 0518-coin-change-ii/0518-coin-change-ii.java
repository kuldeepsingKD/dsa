class Solution {
    int[][] t ;
    int n;

    public int solve(int i, int[] coins, int amount) {

        if(t[i][amount] != -1){
            return t[i][amount];
        }
        if(amount == 0) {
            return 1;
        }

       if (i == n || amount < 0) {
            return 0;
        }

        if(amount < coins[i]) {
            return t[i][amount]=solve(i+1, coins, amount);
        }

        int take = solve(i, coins, amount - coins[i]);
        int skip = solve(i+1, coins, amount);

        return t[i][amount] = take + skip;
    }

    public int change(int amount, int[] coins) {
         n = coins.length;
        t = new int[n+1][amount+1];
        for(int[] a : t) {
            Arrays.fill(a,-1);
        }

        return solve(0,coins, amount);


    }
}