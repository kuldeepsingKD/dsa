class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();
        int n = nums.length;

        for(int i=0; i<n-1; i++) {
            int curr = nums[i];
            int next = nums[i+1];
            while (curr + 1 < next) {
                result.add(curr + 1);
                curr++;
            }
        }

        return result;
    }
}