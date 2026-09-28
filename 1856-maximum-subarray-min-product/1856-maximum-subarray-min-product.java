class Solution {
    public int maxSumMinProduct(int[] nums) {
         int n = nums.length;
        int MOD = 1_000_000_007;
        
        // 1. Prefix Sum array banao range sum O(1) me nikalne ke liye
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        // Boundaries track karne ke liye arrays
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        
        Stack<Integer> st = new Stack<>();
        
        // 2. Previous Smaller Element (Left Boundary)
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                left[i] = st.peek();
            }
            st.push(i);
        }
        
        st.clear(); // Stack khali karo reuse ke liye
        
        // 3. Next Smaller Element (Right Boundary)
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                right[i] = st.peek();
            }
            st.push(i);
        }
        
        // 4. Max Min-Product Calculate Karo
        long maxProduct = 0;
        for (int i = 0; i < n; i++) {
            int leftBound = left[i];
            int rightBound = right[i];
            
             
            long currentSum = prefix[rightBound] - prefix[leftBound + 1];
            
            long currentProduct = (long) nums[i] * currentSum;
            maxProduct = Math.max(maxProduct, currentProduct);
        }
        
        return (int) (maxProduct % MOD);
    }
}