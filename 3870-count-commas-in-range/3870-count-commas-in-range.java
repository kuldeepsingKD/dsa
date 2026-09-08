class Solution {
    public int countCommas(int n) {

        int ans = 0;
        long base = 1000;
        int comma = 1;
      
        while(base <= n) {
            ans += (n - base + 1) * comma;
            base *= 1000;
            comma++;
        }
        
     return ans;
    
    }
}