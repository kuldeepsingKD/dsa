class Solution {
    public long minimumReplacement(int[] nums) {
        int n = nums.length;
        long ans = 0;

        int limit = nums[n-1];

        for(int i = n-2; i >= 0; i--) {
            if(nums[i] <= limit){
                limit = nums[i];
            }else{

                long parts = (nums[i] + limit - 1L)/ limit;

                ans += parts - 1;

                limit = (int)(nums[i]/parts);
            }
        }

        return ans;

    }
}