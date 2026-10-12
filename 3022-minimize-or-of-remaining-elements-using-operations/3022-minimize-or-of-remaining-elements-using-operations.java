class Solution {
    private int getMergeOps(int[] nums, int prefixMask, int target) {
        int mergeOps = 0;
        // flow start karne ke liye current segment ka AND accumulator ko pure mask se init karte hain
        int currentAnd = prefixMask; 

        for (int num : nums) {
            // Current element ko segment ke sath AND karte hain
            currentAnd &= num;

            // Agar target bits successfully 0 ho chuke hain (yaani jo hum chahte hain wahi bacha hai)
            if ((currentAnd | target) == target) {
                // Ek segment complete ho gaya, accumulator ko reset kar do agle segment ke liye
                currentAnd = prefixMask;
            } else {
                // Agar abhi bhi target bits clear nahi hue, toh agle element ke sath merge karna padega
                mergeOps++;
            }
        }
        return mergeOps;
    }
    public int minOrAfterOperations(int[] nums, int k) {
         int ans = 0;
        int prefixMask = 0;

        // Integers 32-bit hote hain, but constraints ke mutabik max value ke liye 30 bits kaafi hain
        for (int i = 30; i >= 0; i--) {
            // Hum i-th bit ko target mask mein add karke test kar rahe hain
            prefixMask |= (1 << i);

            // Agar prefixMask ke saare target bits ko 0 rakhne ke liye k se zyada ops chahiye
            if (getMergeOps(nums, prefixMask, ans) > k) {
                // Toh hum is bit ko 0 nahi rakh sakte, answer mein isse 1 karna padega
                ans |= (1 << i);
            }
        }
        return ans;
    }
}