class Solution {
    int n;
    private boolean isValid(int[] nums, int mid, int p) {
        int i = 0;
        int counts = 0;
        while(i < n-1) {
            if(nums[i+1] - nums[i] <= mid){
                counts++;
                i += 2;
            }else{
                i++;
            }
        }
        return counts >= p;
    }
    public int minimizeMax(int[] nums, int p) {
         n = nums.length;
        Arrays.sort(nums);
        int l = 0;
        int r = nums[n-1] - nums[0];
        int result = Integer.MAX_VALUE;

        while(l <= r){
            int mid = l + (r-l)/2;
            if(isValid(nums, mid, p)){
                result = mid;
                r= mid-1;
            }else{
                l = mid+1;
            }
        }
        return result;
    }
}