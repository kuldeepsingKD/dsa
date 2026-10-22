class Solution {
    public int getWinner(int[] arr, int k) {
        int n = arr.length;

        int currentWinner = arr[0];
        int winStreak = 0;

        for(int i = 1; i < n; i++) {
            if(currentWinner < arr[i]) {
                currentWinner = arr[i];
                winStreak = 1;
            } else {
                winStreak++;
            }

            if(winStreak == k) {
                return currentWinner;
            }
        }

        return currentWinner;
      
    }
}