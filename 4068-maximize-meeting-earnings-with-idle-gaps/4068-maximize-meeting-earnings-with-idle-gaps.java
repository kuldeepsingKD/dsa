class Solution {
    public long maxEarnings(int[][] meetings) {
        int n = meetings.length;
        
        // Step 1: Meetings ko start time ke basis par sort karo
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        
        // f[i] store karega max profit from suffix i...n-1 
        // jab current meeting sequence ki FIRST meeting NAHI hai.
        long[] f = new long[n + 1];
        long maxOverallEarnings = 0;
        
        // Step 2: Suffix DP lagao (Piche se shuru karte hue)
        for (int i = n - 1; i >= 0; i--) {
            long s_i = meetings[i][0];
            long e_i = meetings[i][1];
            long r_i = meetings[i][2];
            
            // Step 3: Binary Search se pehli aisi meeting 'j' dhoodho jiska start time >= e_i ho
            int nextIdx = binarySearch(meetings, i + 1, e_i);
            
            // Case A: Agar yeh meeting sequence ki FIRST meeting NAHI hai (Middle ya Last hai)
            long valAsNotFirst = Math.max(r_i + s_i, r_i + s_i - e_i + f[nextIdx]);
            f[i] = Math.max(f[i + 1], valAsNotFirst);
            
            // Case B: Agar yeh meeting pure sequence ki FIRST meeting hai (ya fir Standalone hai)
            long valAsFirst = Math.max(r_i, r_i - e_i + f[nextIdx]);
            maxOverallEarnings = Math.max(maxOverallEarnings, valAsFirst);
        }
        
        return maxOverallEarnings;
    }
    
    // Helper function: Find first meeting index where start time >= targetEndTime
    private int binarySearch(int[][] meetings, int start, long targetEndTime) {
        int low = start;
        int high = meetings.length;
        int ans = meetings.length;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (meetings[mid][0] >= targetEndTime) {
                ans = mid;
                high = mid; // Aur peeche try karo
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}