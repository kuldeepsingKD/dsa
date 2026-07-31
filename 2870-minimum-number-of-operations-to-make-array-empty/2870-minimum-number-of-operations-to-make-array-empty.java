class Solution {
    public int minOperations(int[] nums) {
           HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequency of each task
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
           int rounds = 0;

        // Iterate over frequencies
        for (int count : map.values()) {

            if (count == 1) {
                return -1;
            }

            if (count % 3 == 0) {
                rounds += count / 3;
            } else {
                rounds += count / 3 + 1;
            }
        }

        return rounds;
    }
}