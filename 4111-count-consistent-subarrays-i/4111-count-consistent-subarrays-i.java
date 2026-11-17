class Solution {
    public int countConsistent(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int currMin = nums[i];
            int currAnd = nums[i];

            for (int j = i; j < n; j++) {
                currAnd &= nums[j];
                currMin = Math.min(currMin, nums[j]);

                if (currMin == currAnd) {
                    count++;
                }

            }
        }
        return count;
    }
}