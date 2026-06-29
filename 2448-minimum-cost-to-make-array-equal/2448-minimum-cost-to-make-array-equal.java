class Solution {
    public long findCost(int[] nums, int[] cost, int target){
        long result = 0; 
        for(int i = 0; i< nums.length; i++){
            result += (long)Math.abs(nums[i] - target) * cost[i];
        }
        return result;
    }
    public long minCost(int[] nums, int[] cost) {
        long answer = Integer.MAX_VALUE;
         int left = Arrays.stream(nums).min().getAsInt();
        int right = Arrays.stream(nums).max().getAsInt();

        while(left <= right) {
            int mid = left + (right - left)/2;

            long cost1 = findCost(nums, cost, mid);
            long cost2 = findCost(nums, cost, mid+1);

            answer = Math.min(cost1, cost2);

            if(cost2 > cost1){
                right = mid -1;
            }else{
                left = mid+1;
            }
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}