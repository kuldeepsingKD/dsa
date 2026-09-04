class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double maxSum = -Double.MAX_VALUE; 
        double currentSum = 0;
        int left = 0;

        for(int right = 0; right < n; right++) {
            
            currentSum += nums[right];

            if(right - left + 1 == k) {

                maxSum = Math.max(maxSum, currentSum);

                currentSum -= nums[left];


                left++;
            }
        }

        return maxSum/k;

    }
}