class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int Q = queries.length;
          int[] diff = new int[n+1];

        for (int i = 0; i < Q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
         

          if(l < n) {
            diff[l] += 1;
          }
            if (r + 1 < n)
                diff[r + 1] -= 1;
        }

        int currSum = 0;
        for(int i = 0; i < n; i++ ) {
            currSum += diff[i];
            diff[i] = currSum;

            if(nums[i] - diff[i] > 0) {
                return false;
            }
        }
        return true;
    }
}