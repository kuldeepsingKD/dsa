class Solution {
    private int helper(int[] nums, int L, int M) {
        int n = nums.length;
         
        int lBlockSum = 0;
        int mBlockSum = 0;

        for(int i = 0; i < L+M; i++) {
            if(i < L){
                lBlockSum += nums[i];
            }else{
                mBlockSum  += nums[i];
            }
        }
        
        int maxLeftSubSum = lBlockSum;
        int result = maxLeftSubSum + mBlockSum;

        for(int mEnd = L+M; mEnd < n; mEnd++) {

            lBlockSum += nums[mEnd - M] - nums[mEnd - M -L];
            mBlockSum += nums[mEnd] - nums[mEnd - M];

            maxLeftSubSum = Math.max(maxLeftSubSum, lBlockSum);

            result = Math.max(result, maxLeftSubSum + mBlockSum);



        }

        return result;
 
    }
    public int maxSumTwoNoOverlap(int[] nums, int L, int M) {
        int n = nums.length;
        
        return Math.max(helper(nums, L, M), helper(nums, M, L));
    }
}