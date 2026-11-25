class Solution {
    long base = 100007;
    long mod = 100000009;
    int n;

    private boolean isPossible(int[] nums, int k) {
        HashMap<Long, Integer> map = new HashMap<>();

        long hash = 0;
        long power = 1;

        for(int i = 0; i < k; i++) {
            hash = (hash*base + nums[i]) % mod;

            if(i < k-1) {
                power = (power*base) % mod;
            }

        }

        map.put(hash, 1);

        for(int i = k; i < n; i++) {
            hash = (hash - nums[i-k]*power % mod + mod) % mod;
            hash = (hash*base + nums[i]) % mod;

            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }

        for(int val : map.values()) {
            if(val == 1) {
                return true;
            }
        }
        return false;
    }
    public int smallestUniqueSubarray(int[] nums) {
        n = nums.length;
        int left = 1,  right = n;
        int ans = n;

        while(left <= right) {
            int mid = left + (right - left)/2;
            if(isPossible(nums, mid)) {
                ans = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return ans;
    }
}