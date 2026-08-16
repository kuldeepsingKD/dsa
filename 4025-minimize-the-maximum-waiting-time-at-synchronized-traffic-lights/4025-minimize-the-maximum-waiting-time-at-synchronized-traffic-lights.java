class Solution {
    private boolean isValid(int W, int[] arrivalTime, int maxGreen, int period) {
        for(int t : arrivalTime) {
            int r = t % period;


            if(r < maxGreen){
                continue;
            }

            if(period - r > W) {
                return false;
            }
        }
        return true;
    }
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        int maxGreen = 0;
        for(int light : lights) {
            maxGreen = Math.max(maxGreen, light);

        }

        int low = 0;
        int high = period;
        int ans = 0;

        while(low <= high) {
            int mid = low + (high - low)/2;

            if(isValid(mid, arrivalTime, maxGreen, period)) {
                ans = mid;
                high = mid-1;
            }else {
                low = mid+1;
            }
        }

        return ans;
    }
}