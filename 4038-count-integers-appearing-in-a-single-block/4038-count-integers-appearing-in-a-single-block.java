class Solution {
    public int countSpecialIntegers(int[] nums) {
       Map<Integer, Integer> firstOccurrence = new HashMap<>();
        Map<Integer, Integer> lastOccurrence = new HashMap<>();
        Map<Integer, Integer> frequency = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            // Record first occurrence
            if (!firstOccurrence.containsKey(num)) {
                firstOccurrence.put(num, i);
            }
            // Constantly update last occurrence
            lastOccurrence.put(num, i);
            
            // Increment total frequency
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        
        int specialCount = 0;
        
        // Check the condition for each unique number
        for (int num : frequency.keySet()) {
            int first = firstOccurrence.get(num);
            int last = lastOccurrence.get(num);
            int count = frequency.get(num);
            
            // If the span matches the total frequency, it's a single contiguous block
            if (last - first + 1 == count) {
                specialCount++;
            }
        }
        
        return specialCount;

    }
}