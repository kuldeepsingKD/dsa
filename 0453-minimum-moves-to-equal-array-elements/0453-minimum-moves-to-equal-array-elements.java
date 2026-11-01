class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;

        int min = Integer.MAX_VALUE;
        int ops = 0;

        for(int i = 0; i < n; i++) {
             min = Math.min(min, nums[i]);
        }

        for(int i = 0; i < n; i++) {
            

            ops += (nums[i]-min);
        }

        return ops;
    }
}