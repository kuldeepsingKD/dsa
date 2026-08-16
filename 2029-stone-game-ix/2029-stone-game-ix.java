class Solution {
    public boolean stoneGameIX(int[] stones) {
        int c0 = 0;
        int c1 = 0;
        int c2 = 0;

        for(int s : stones) {
            int rem = s % 3;

            if(rem == 0){
                c0++;
            }else if(rem == 1) {
                c1++;
            }else{
                c2++;
            }
        
        }

       return (c0 % 2 == 0) ? (c1 > 0 && c2 > 0) : Math.abs(c1 - c2) > 2;
    }
}