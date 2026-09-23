class Solution {
        
    public void wiggleSort(int[] nums) {
       int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);
        
        // Find the mid-point to split smaller and larger halves
        // Using (n + 1) / 2 ensures the left half gets the extra element if n is odd
        int left = (n - 1) / 2; 
        int right = n - 1;
        
        // Write back to the original array from the end of both halves
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = copy[left--]; // Even indices get smaller elements
            } else {
                nums[i] = copy[right--]; // Odd indices get larger elements
            }
        }
    }
}