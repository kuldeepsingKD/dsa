class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int i =0, j = 0;

        while(i < nums.length) {
            int ele = nums[i];
            map.put(ele, map.getOrDefault(ele, 0) +1);

            while(map.get(ele) > k) {
                int frontEle = nums[j];
                int freq = map.get(frontEle);
                map.put(frontEle, freq-1);
                j++;
            }

            result = Math.max(result, (i-j)+1);
            i++;
        }

     return result;
    }
}