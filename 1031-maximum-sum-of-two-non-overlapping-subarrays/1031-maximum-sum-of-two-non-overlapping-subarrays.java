class Solution {
    private int helper(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;

        // prefix sum array
        int[] prefix = new int[n];
        prefix[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int maxFirst = prefix[firstLen - 1];   // max sum of firstLen window
        int maxSum = 0;

        for (int i = firstLen + secondLen - 1; i < n; i++) {

            // update maxFirst window
            int firstWindowSum = prefix[i - secondLen] - 
                    (i - secondLen - firstLen >= 0 ? prefix[i - secondLen - firstLen] : 0);

            maxFirst = Math.max(maxFirst, firstWindowSum);

            // current second window sum
            int secondWindowSum = prefix[i] - prefix[i - secondLen];

            maxSum = Math.max(maxSum, maxFirst + secondWindowSum);
        }

        return maxSum;
    }
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
         return Math.max(helper(nums, firstLen, secondLen), helper(nums, secondLen, firstLen));
    }
}