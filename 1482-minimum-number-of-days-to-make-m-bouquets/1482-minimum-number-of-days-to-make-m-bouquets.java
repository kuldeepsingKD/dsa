class Solution {
    public int canMakeBouq(int[] bloomDay, int mid, int k){
        int bouqCount = 0;
        int consecutive_count = 0;

        for(int i = 0; i < bloomDay.length; i++){
            if(bloomDay[i] <= mid){
                consecutive_count++;
            } else {
                consecutive_count = 0;

            }
            if(consecutive_count == k){
                bouqCount++;
                consecutive_count = 0;
            }
        }
        return bouqCount;
    }
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        int l = 0;
        int r = Arrays.stream(bloomDay).max().getAsInt();
        int minDay = -1;

        while(l <= r){
            int mid = l+(r-l)/2;
            if(canMakeBouq(bloomDay, mid, k) >= m){
                minDay = mid;
                r = mid-1;
            }else{
                l = mid+1;
            }
        }

        return minDay;
    }
}