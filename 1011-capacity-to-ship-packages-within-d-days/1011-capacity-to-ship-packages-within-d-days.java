class Solution {
    public boolean canShip(int[] weights, int days, int capacity) {
        int requireDays = 1;
        int currentLoad = 0;

        for(int weight : weights){
            if(currentLoad + weight > capacity){
                requireDays++;
                currentLoad = 0;
            }
            currentLoad += weight;
        }

        return requireDays <= days;
    }
    public int shipWithinDays(int[] weights, int days) {
        int l = 0;
        int r = 0;
         
        for(int weight : weights){
            l = Math.max(l, weight);
            r += weight;
        } 
        while(l < r){
            int mid = l + (r-l)/2;
            if(canShip(weights, days, mid)){
                r = mid;
            } else {
                l = mid+1;
            }
        }

        return l;
    }
}