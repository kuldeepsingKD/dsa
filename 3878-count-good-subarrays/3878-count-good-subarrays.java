class Solution {
    public long countGoodSubarrays(int[] nums) {
         int n = nums.length;
        int[] l = new int[n];
        int[] r = new int[n];
        
         
        Arrays.fill(l, -1);
        Arrays.fill(r, n);
        
        Deque<Integer> stk = new ArrayDeque<>();
        
        // Step 1: Find Left Boundary l[i] for each element
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            // Stack se un elements ko hatao jo x ke subset hain aur x se chhote hain
            while (!stk.isEmpty() && nums[stk.peek()] < x && (nums[stk.peek()] | x) == x) {
                stk.pop();
            }
            l[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(i);
        }
        
        stk.clear(); // Clear stack for right boundary calculation
        
        // Step 2: Find Right Boundary r[i] for each element
        for (int i = n - 1; i >= 0; i--) {
            // Stack se un elements ko hatao jo nums[i] ke subset hain
            while (!stk.isEmpty() && (nums[stk.peek()] | nums[i]) == nums[i]) {
                stk.pop();
            }
            r[i] = stk.isEmpty() ? n : stk.peek();
            stk.push(i);
        }
        
        // Step 3: Calculate total contribution of each element
        long totalGoodSubarrays = 0;
        for (int i = 0; i < n; i++) {
            totalGoodSubarrays += (long) (i - l[i]) * (r[i] - i);
        }
        
        return totalGoodSubarrays;
    }
}