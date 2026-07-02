class Solution {
    private boolean possible(int[] batteries, long mid_min, int n) {
        long target_min = n * mid_min;
        long sum = 0;
        for(int i = 0; i < batteries.length; i++) {
            sum += Math.min(batteries[i], mid_min);
            if(sum >= target_min)
            return true;
        }
        return false;
    }
    public long maxRunTime(int n, int[] batteries) {
        long l = Arrays.stream(batteries).min().getAsInt();
        long totalsum_min = 0;
        for(int mints : batteries){
            totalsum_min += mints;
        }

        long r = totalsum_min/n;
        long result = 0;
        while(l <= r){
            long mid_min = l + (r-l)/2;
            if(possible(batteries, mid_min, n)){
                result = mid_min;
                l = mid_min + 1;
            }else {
                r = mid_min-1;
            }
        } 

        return result;

    }
}