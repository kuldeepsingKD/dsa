class Solution {
    int n;
    int[][] t ;
    public int countSubsetSum(int n, int sum, int[] nums) {
        if(n == 0) {
            return (sum == 0) ? 1 : 0;
        }

        if(t[n][sum] != -1) {
            return t[n][sum];
        }

        int skip = countSubsetSum(n - 1, sum, nums);

        int take = 0;
        if(nums[n-1] <= sum) {
            take = countSubsetSum(n-1, sum- nums[n-1], nums);
        }

        return t[n][sum] = skip + take;
    }
    public int findTargetSumWays(int[] nums, int target) {
          n = nums.length;

          t = new int[21][1001];
          for (int[] row : t) {
    Arrays.fill(row, -1);
}
        int sum = 0;
        target =  Math.abs(target);

        for(int x : nums) {
            sum += x;
        }

        if(((sum + target)%2) != 0) {
            return 0;
        }

        int s1 = (sum + target)/2;

        return countSubsetSum(n, s1, nums);
    }
}