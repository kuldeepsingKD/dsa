class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);

        for(int m : asteroids) {
            if(mass < m) {
                return false;
            }else if(mass >= 1e5){
                 return true;
            }else{
                mass += m;
            }
        }

        return true;
    }
}