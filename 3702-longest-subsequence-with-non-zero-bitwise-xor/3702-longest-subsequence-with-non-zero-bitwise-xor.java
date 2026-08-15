class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;
        for (int x : nums) {
            totalXor ^= x;
            if (x != 0) hasNonZero = true;
        }
        if (totalXor != 0) return nums.length;
        return hasNonZero ? nums.length - 1 : 0;
    }
}