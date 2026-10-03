class Solution {
    public long maxValue(int[] nums) {
        long original = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if((i % 2) == 0) {
                original += nums[i];
            }else{
                original -= nums[i];
            }
        }

        if(n < 2) {
            return original;
        }

        long prefix = 0;
        long maxEven = 0, maxOdd = Long.MIN_VALUE, ans = Long.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) {
                prefix += nums[i];

            }else{
                prefix -= nums[i];
            }

            if((i+1) % 2 == 0) {
                long temp = Math.min(prefix, prefix - maxEven);
                ans = Math.min(ans, temp);
            }else if(maxOdd != Long.MIN_VALUE) {
                ans = Math.min(prefix - maxOdd, ans);
            }

            if((i+1)%2 == 0) {
                maxEven = Math.max(prefix, maxEven);
            }else{
                maxOdd = Math.max(maxOdd, prefix);
            }
        }

        return Math.max(original, original - 2*ans);
    }
}