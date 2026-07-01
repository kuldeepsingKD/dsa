class Solution {
    public double canPossible(int[] dist, int mid_speed){
        double time = 0.0;
        int n = dist.length;

        for(int i = 0; i < n-1; i++) {
            double t = (double)dist[i]/(double)mid_speed;
            time += Math.ceil(t);
        } 

        time += (double)dist[n-1]/(double)mid_speed;
        return time;
    }
    public int minSpeedOnTime(int[] dist, double hour) {
        int l = 1;
        int r = 10000000;
        int minSpeed = -1;

        while(l <= r){
            int mid_speed = l + (r-l)/2;
            if(canPossible(dist, mid_speed) <= hour){
                minSpeed = mid_speed;
                r = mid_speed -1;
            }else{
                l = mid_speed + 1;
            }
        }

        return minSpeed;
    }
}