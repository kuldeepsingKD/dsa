class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int s = snums.length;
        int e = 0;

        for(int i = 0; i < snums.length; i++) {
            if(snums[i] != nums[i]) {
                s = Math.min(s, i);
                e = Math.max(e, i);
            }
        }

        return (e - s >= 0) ? e - s + 1 : 0;
    }
}