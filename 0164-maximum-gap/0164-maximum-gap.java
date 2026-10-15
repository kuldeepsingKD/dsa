class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;

        if(nums == null || n < 2) {
            return 0;
        }

        Arrays.sort(nums);

        int max = nums[1] - nums[0];

        for(int i = 2; i < n; i++) {
            max = Math.max(max, nums[i] - nums[i-1]);
        }

        return max;
    }
}