class Solution {
    public int countDominantElements(int[] nums) {
        int n = nums.length;
        
         
        if(n == 1) {
            return 1;
        }

        int ans = (nums[0] >= nums[1] ? 1 : 0) + (nums[n-1] >= nums[n-2] ? 1 : 0);

        

        for(int i = 1; i < n-1; i++) {
            if (nums[i] >= (nums[i-1] + nums[i+1]+ 1)/2){
                ans++;
            }
        }


        return ans;

         
         
    }
}