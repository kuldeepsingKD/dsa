class Solution {
    public int countGoodRotations(int[] nums) {
        int n = nums.length;
        long firstHalfSum = 0;
        long totalSum = 0;
        int half = n/2;

        for(int i = 0; i < n; i++) {
            totalSum += nums[i];
            if(i < half) {
                firstHalfSum += nums[i];
            }
        }

        int score = 0;

        for(int i = 0; i < n; i++) {
            if(2*firstHalfSum > totalSum) {
                score++;
            }


            firstHalfSum -= nums[i];
            firstHalfSum += nums[(i + half) % n];
        }

        return score;
    }
}