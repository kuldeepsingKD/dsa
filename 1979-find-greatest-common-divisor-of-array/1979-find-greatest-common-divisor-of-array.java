class Solution {
    private int gcd(int a, int b) {
        while (b != 0) {
            int rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return gcd(nums[0], nums[n-1]);
    }
}