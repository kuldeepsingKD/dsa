class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0,-1);

        for(int i = 0; i < n; i++) {
            sum += nums[i];
            map.put(sum, i);
        }
        if(sum < x) {
            return -1;
        }

        int longestSubArray = Integer.MIN_VALUE;
        int rest = sum - x;
        sum = 0;

        for(int i = 0; i < n; i++) {
            sum += nums[i];
            if(map.containsKey(sum - rest)) {
                longestSubArray = Math.max(longestSubArray, i - map.get(sum - rest));
            }
        }

        return longestSubArray == Integer.MIN_VALUE ? -1 : n - longestSubArray;
    }
}