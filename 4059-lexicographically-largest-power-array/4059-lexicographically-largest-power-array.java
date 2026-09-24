class Solution {
    public int[] largestPower(int[] nums) {
         int n = nums.length;
        
        // 1. Remaining elements ki list banayein
        List<Integer> remaining = new ArrayList<>();
        for (int x : nums) {
            remaining.add(x);
        }
        
        int[] perm = new int[n];
        int permIdx = 0;
        
        // Shuruat mein sabhi 15 bits (0 se 14) alive hain
        int aliveMask = (1 << 15) - 1;
        
        // 2. Greedy Batch Selection Loop (Maximum 16 baar chalega)
        while (!remaining.isEmpty()) {
            int maxV = -1;
            
            // Is mask ke sath sabse badi value dhoondhein
            for (int x : remaining) {
                int masked = x & aliveMask;
                if (masked > maxV) {
                    maxV = masked;
                }
            }
            
            // Jo elements is max value ko match karte hain, unhe ek sath perm mein daal dein
            List<Integer> nextRemaining = new ArrayList<>();
            for (int x : remaining) {
                if ((x & aliveMask) == maxV) {
                    perm[permIdx++] = x;
                } else {
                    nextRemaining.add(x);
                }
            }
            
            remaining = nextRemaining;
            // Mask ko update karein (jo bits is batch mein 0 thin, woh hamesha ke liye dead ho gayin)
            aliveMask = maxV;
        }
        
        // 3. Final Permutation se Power Array calculate karein
        int[] power = new int[15];
        for (int b = 0; b < 15; b++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (((perm[i] >> b) & 1) == 1) {
                    count++;
                } else {
                    break; // Jaise hi chain tooti, ruk jayein
                }
            }
            power[14 - b] = count;
        }
        
        return power;
    }
}