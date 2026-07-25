class Solution {
    private boolean isPossible(int mid, int[] nums, int k) {
        int n = nums.length;

        int count = 0;
        for(int i =0; i < n; ) {
            if(nums[i] <= mid) {
              count++;
              i += 2;
            }else{
                i++;
            }
        }
        return count >= k;
    }
    public int minCapability(int[] nums, int k) {
        int result = 0;
        int maxC = 0;
        for(int num : nums) {
            maxC =Math.max(maxC, num);
        }
        int l = 1;
        int r = maxC;

        while(l <= r) {
            int mid = l+(r-l)/2;
            if(isPossible(mid, nums, k)) {
                result = mid;
                r = mid-1;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }
}