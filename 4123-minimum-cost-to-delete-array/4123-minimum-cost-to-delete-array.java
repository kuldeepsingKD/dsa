class Solution {
    public long minCost(int[] nums) {
        int n = nums.length;
        long ans = nums[0] + nums[n-1];
        long sum = 0;
        long closestNeg = Long.MIN_VALUE;
        long smallestPos = Long.MAX_VALUE;

        int cnt = 0;

        for(int i = 1; i < n-1; i++) {
            if(nums[i] < 0) {
                sum += nums[i];
                cnt++;
                closestNeg = Math.max(closestNeg, (long)nums[i]);
            }else{
                smallestPos = Math.min(smallestPos, (long)nums[i]);
            }
        }

        if(cnt % 2 == 1) {
            sum += Math.min(-closestNeg, smallestPos);
        }

        return sum + ans;
    }
}