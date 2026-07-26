class Solution {
    public long maxProduct(int[] nums) {
        int n = nums.length;

        long max1 = 0;
        long max2 = 0;

        for (int num : nums) {
            long value = Math.abs((long) num);

            if (value > max1) {
                max2 = max1;
                max1 = value;
            } else if (value > max2) {
                max2 = value;
            }
        }

        return max1 * max2 * 100000L;

    }
}