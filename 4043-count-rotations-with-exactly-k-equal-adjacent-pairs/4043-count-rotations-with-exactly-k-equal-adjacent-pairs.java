class Solution {
    public int countRotations(String s, int k) {
         int n = s.length();
        // Handle edge case for empty or single-character strings
        if (n <= 1) {
            return (k == 0) ? 1 : 0; 
        }

        // Double the string to handle cyclic shifts naturally
        String doubledStr = s + s;
        int doubledLen = doubledStr.length();
        
        // Track adjacent matches: matches[i] = 1 if characters at i and i+1 are equal
        int[] matches = new int[doubledLen - 1];
        for (int i = 0; i < doubledLen - 1; i++) {
            if (doubledStr.charAt(i) == doubledStr.charAt(i + 1)) {
                matches[i] = 1;
            }
        }
        
        // Calculate the score of the initial window (the original string s)
        // A window of length n contains exactly n - 1 adjacent pairs
        int currentScore = 0;
        for (int i = 0; i < n - 1; i++) {
            currentScore += matches[i];
        }
        
        int validRotationsCount = 0;
        if (currentScore == k) {
            validRotationsCount++;
        }
        
        // Slide the window across all remaining n - 1 rotations
        for (int i = 1; i < n; i++) {
            // Remove the pair that left the window on the left side
            currentScore -= matches[i - 1];
            // Add the new pair that entered the window on the right side
            currentScore += matches[i + n - 2];
            
            if (currentScore == k) {
                validRotationsCount++;
            }
        }
        
        return validRotationsCount;
    }
}