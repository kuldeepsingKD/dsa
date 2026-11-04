class Solution {
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;

        int m = n/2;
        long ops = 0;

        ops += Math.abs(nums[m] - k);

        if(nums[m] > k) {
            for(int i = m-1; i >= 0; i--) {
                if(nums[i] > k) {
                    ops += (nums[i] - k);
                }else{
                    break;
                }
            }
        }else if(nums[m] < k) {
            for(int i = m+1; i < n; i++) {
                if(nums[i] < k) {
                    ops += (k - nums[i]);
                }else {
                    break;
                }
            }
        }

        return ops;
    }
}