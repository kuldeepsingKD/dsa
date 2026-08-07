 

public class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Count prime factors of t
        int[] counts = new int[4]; // index map: 0->2, 1->3, 2->5, 3->7
        long tempT = t;
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (tempT % primes[i] == 0) {
                counts[i]++;
                tempT /= primes[i];
            }
        }
        
        // If t has prime factors > 7, it's impossible to form using digits 1-9
        if (tempT > 1) {
            return "-1";
        }
        
        int n = num.length();
        int hasZeroAt = -1;
        
        // Step 2: Track prefix matching
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) {
                hasZeroAt = i;
                break;
            }
            reduce(counts, d); // consume factors forward (can go negative)
        }
        
        // Case A: The entire string matches 'num' perfectly and satisfies t
        if (hasZeroAt == -1 && minDigitsNeeded(counts) == 0) {
            return num;
        }
        
        // Step 3: Backtrack to find the optimal increment point
        int startIdx = (hasZeroAt != -1) ? hasZeroAt : n - 1;
        
        // If we didn't hit a zero, un-consume the last digit to try incrementing it
        if (hasZeroAt == -1) {
            restore(counts, num.charAt(n - 1) - '0');
        }
        
        for (int i = startIdx; i >= 0; i--) {
            int currentDigit = (hasZeroAt == -1 || i < hasZeroAt) ? (num.charAt(i) - '0') : 0;
            
            // Try increasing the digit at index i
            for (int d = currentDigit + 1; d <= 9; d++) {
                reduce(counts, d);
                
                int slotsLeft = n - 1 - i;
                if (minDigitsNeeded(counts) <= slotsLeft) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(d);
                    sb.append(buildSuffix(counts, slotsLeft));
                    return sb.toString();
                }
                
                restore(counts, d); // rollback if it doesn't fit
            }
            
            // Roll back index i-1's original digit before moving up to index i-1
            if (i > 0 && (hasZeroAt == -1 || i - 1 < hasZeroAt)) {
                restore(counts, num.charAt(i - 1) - '0');
            }
        }
        
        // Case C: If no modifications within length 'n' work, expand length
        // Re-initialize counts directly from original t factors
        int[] originalCounts = new int[4];
        tempT = t;
        for (int i = 0; i < 4; i++) {
            while (tempT % primes[i] == 0) {
                originalCounts[i]++;
                tempT /= primes[i];
            }
        }
        
        int totalNeeded = minDigitsNeeded(originalCounts);
        int targetLen = Math.max(n + 1, totalNeeded);
        return buildSuffix(originalCounts, targetLen);
    }
    
    // Exact factor deduction (allows tracking factor surpluses into negative values)
    private void reduce(int[] req, int d) {
        if (d == 2) req[0] -= 1;
        else if (d == 3) req[1] -= 1;
        else if (d == 4) req[0] -= 2;
        else if (d == 5) req[2] -= 1;
        else if (d == 6) { req[0] -= 1; req[1] -= 1; }
        else if (d == 7) req[3] -= 1;
        else if (d == 8) req[0] -= 3;
        else if (d == 9) req[1] -= 2;
    }
    
    // Perfect symmetric addition rollback
    private void restore(int[] req, int d) {
        if (d == 2) req[0] += 1;
        else if (d == 3) req[1] += 1;
        else if (d == 4) req[0] += 2;
        else if (d == 5) req[2] += 1;
        else if (d == 6) { req[0] += 1; req[1] += 1; }
        else if (d == 7) req[3] += 1;
        else if (d == 8) req[0] += 3;
        else if (d == 9) req[1] += 2;
    }
    
    // Evaluates requirements safely by treating negative/surplus counts as 0
    private int minDigitsNeeded(int[] req) {
        int c2 = Math.max(0, req[0]);
        int c3 = Math.max(0, req[1]);
        int c5 = Math.max(0, req[2]);
        int c7 = Math.max(0, req[3]);
        
        int n9 = c3 / 2;
        int rem3 = c3 % 2;
        
        int n8 = c2 / 3;
        int rem2 = c2 % 3;
        
        int n6 = 0, n4 = 0;
        if (rem2 == 2 && rem3 == 1) { 
            n6 = 1; n4 = 1;
            rem2 = 0; rem3 = 0;
        } else if (rem2 == 1 && rem3 == 1) { 
            n6 = 1;
            rem2 = 0; rem3 = 0;
        } else if (rem2 == 2) { 
            n4 = 1;
            rem2 = 0;
        }
        
        return n9 + n8 + n6 + n4 + rem2 + rem3 + c5 + c7;
    }
    
    // Builds the optimal suffix string using normalized positive factor targets
    private String buildSuffix(int[] req, int length) {
        int c2 = Math.max(0, req[0]);
        int c3 = Math.max(0, req[1]);
        int c5 = Math.max(0, req[2]);
        int c7 = Math.max(0, req[3]);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < c7; i++) sb.append('7');
        for (int i = 0; i < c5; i++) sb.append('5');
        
        int n9 = c3 / 2;
        for (int i = 0; i < n9; i++) sb.append('9');
        c3 %= 2;
        
        if (c3 == 1) {
            if (c2 % 3 == 1) { 
                sb.append('6');
                c2 -= 1;
            } else if (c2 % 3 == 2) { 
                sb.append('6').append('2');
                c2 -= 2;
            } else {
                sb.append('3');
            }
        }
        
        int n8 = c2 / 3;
        for (int i = 0; i < n8; i++) sb.append('8');
        c2 %= 3;
        
        if (c2 == 2) sb.append('4');
        else if (c2 == 1) sb.append('2');
        
        char[] chars = sb.toString().toCharArray();
        Arrays.sort(chars);
        
        StringBuilder result = new StringBuilder();
        int onesNeeded = length - chars.length;
        for (int i = 0; i < onesNeeded; i++) {
            result.append('1');
        }
        result.append(chars);
        
        return result.toString();
    }
}