class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int max = 0;
        int ops = 0;

        for(int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
        }

        for(int i = 0; i < n; i++) {
            ops += (max - nums[i]);
        }


        return ops;
    }
}