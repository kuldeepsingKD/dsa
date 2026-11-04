class Solution {
     private static void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    // Helper function: Multiset se element remove karne ke liye
    private static void remove(TreeMap<Integer, Integer> map, int val) {
        int freq = map.get(val);
        if (freq == 1) map.remove(val);
        else map.put(val, freq - 1);
    }
    public double[] medianSlidingWindow(int[] nums, int x) {
        int n = nums.length;
        // x size ke total (n - x + 1) subarrays banenge
        double[] medians = new double[n - x + 1]; 
        
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftSize = 0, rightSize = 0;

        int i = 0; // Left pointer (Window start)
        for (int j = 0; j < n; j++) { // Right pointer (Window end)
            
            // 1. Naya element insert karein
            if (leftSize == 0 || nums[j] <= left.lastKey()) {
                add(left, nums[j]);
                leftSize++;
            } else {
                add(right, nums[j]);
                rightSize++;
            }

            // 2. Strict Balancing: leftSize ko rightSize ke barabar ya 1 bada rakhna hai
            if (leftSize > rightSize + 1) {
                int maxLeft = left.lastKey();
                remove(left, maxLeft); leftSize--;
                add(right, maxLeft); rightSize++;
            } else if (leftSize < rightSize) {
                int minRight = right.firstKey();
                remove(right, minRight); rightSize--;
                add(left, minRight); leftSize++;
            }

            // 3. Agar window ka size exactly 'x' ho jaye
            if (j - i + 1 == x) {
                // Median nikalne ka logic (Odd vs Even size)
                if (x % 2 != 0) {
                    medians[i] = left.lastKey();
                } else {
                    // Agar even hai toh left ka max aur right ka min ka average
                    medians[i] = ((double) left.lastKey() + right.firstKey()) / 2.0;
                }

                // Window ko aage badhane ke liye purana element (nums[i]) remove karein
                if (left.containsKey(nums[i])) {
                    remove(left, nums[i]);
                    leftSize--;
                } else {
                    remove(right, nums[i]);
                    rightSize--;
                }
                
                // Remove karne ke baad phir se balance check karein
                if (leftSize > rightSize + 1) {
                    int maxLeft = left.lastKey();
                    remove(left, maxLeft); leftSize--;
                    add(right, maxLeft); rightSize++;
                } else if (leftSize < rightSize) {
                    int minRight = right.firstKey();
                    remove(right, minRight); rightSize--;
                    add(left, minRight); leftSize++;
                }

                i++; // Window ka starting pointer aage badhayein
            }
        }
        return medians;
    }
}