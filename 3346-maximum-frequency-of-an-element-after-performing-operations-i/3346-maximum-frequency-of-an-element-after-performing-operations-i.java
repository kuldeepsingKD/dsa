class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int maxVal = 0;
        for(int num : nums) {
            maxVal = Math.max(num, maxVal);
        }

        maxVal = maxVal + k;

        int[] diff = new int[maxVal+2];
        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int l  =Math.max(nums[i] - k, 0);
            int r = Math.min(nums[i] + k, maxVal);

            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            diff[l]++;
            diff[r+1]--;
        } 

        int result = 1;


        for(int target = 0; target <= maxVal; target++ ){
            diff[target] += (target > 0 ? diff[target - 1] : 0);

            int targetFreq = map.getOrDefault(target, 0);
            int needConversion = diff[target] - targetFreq;

            int maxPossiblefreq = Math.min(needConversion,  numOperations);

            result = Math.max(result, targetFreq + maxPossiblefreq);
        }

        return result;
    }
}