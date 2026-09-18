import java.util.*;

class Solution {
    // Global lists to hold precomputed palindromes
    private static final List<Long> evenPalindromes = new ArrayList<>();
    private static final List<Long> oddPalindromes = new ArrayList<>();

    // 1. Precomputation: Ek baar saare palindromes generate kar ke sort kar lete hain
    static {
        // 1 se 100,000 tak ke prefixes se hum 10^10 tak ke saare palindromes generate kar sakte hain
        for (int i = 1; i <= 100000; i++) {
            String s = Integer.toString(i);
            String rev = new StringBuilder(s).reverse().toString();
            
            // Candidate 1: Odd length palindrome (e.g., 12 -> 12 + 1 = 121)
            long p1 = Long.parseLong(s + rev.substring(1));
            if (p1 % 2 == 0) evenPalindromes.add(p1);
            else oddPalindromes.add(p1);
            
            // Candidate 2: Even length palindrome (e.g., 12 -> 12 + 21 = 1221)
            long p2 = Long.parseLong(s + rev);
            if (p2 % 2 == 0) evenPalindromes.add(p2);
            else oddPalindromes.add(p2);
        }
        
        // Binary search ke liye dono lists ko sort karna jaroori hai
        Collections.sort(evenPalindromes);
        Collections.sort(oddPalindromes);
    }

    public long minOperations(int[] nums) {
        long totalOperations = 0;
        
        for (int num : nums) {
            // Parity check ke hisab se sahi list select karein
            List<Long> targetList = (num % 2 == 0) ? evenPalindromes : oddPalindromes;
            
            // Binary search se closest elements nikalna
            totalOperations += findMinOpsForNumber(num, targetList);
        }
        
        return totalOperations;
    }

    // Custom Lower Bound Binary Search
    private long findMinOpsForNumber(long target, List<Long> list) {
        int low = 0;
        int high = list.size();
        
        // Lower bound find karne ke liye loop
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= target) {
                high = mid; // Target se bada ya barabar hai, toh left shift karein
            } else {
                low = mid + 1; // Target se chota hai, toh right shift karein
            }
        }
        
        // Yahan 'low' hume pehla element dega jo >= target hai (i.e., 'pos')
        int pos = low;
        long minDiff = Long.MAX_VALUE;
        
        // Candidate 1: Check elements at 'pos' (just greater than or equal to target)
        if (pos < list.size()) {
            minDiff = Math.min(minDiff, list.get(pos) - target);
        }
        
        // Candidate 2: Check elements at 'pos - 1' (just smaller than target)
        if (pos > 0) {
            minDiff = Math.min(minDiff, target - list.get(pos - 1));
        }
        
        // Ek operation mein +2 ya -2 ho sakta hai, toh operations = difference / 2
        return minDiff / 2;
    }
}