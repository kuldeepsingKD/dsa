class Solution {
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
          int mxPenalty = 0;
        int mx = 0;
        for(int i : lights){
            mx = Math.max(mx, i);
        }
        for(int i : arrivalTime){
            if(i >= mx){
                int a = i%period;
                if(a >= mx){
                    mxPenalty = Math.max(mxPenalty, period-a);
                }
            }
        }
        return mxPenalty;
    }
}