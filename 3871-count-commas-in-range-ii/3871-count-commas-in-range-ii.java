class Solution {
    public long countCommas(long n) {
          long ans = 0;
        long start = 1000;
        int comma = 1;
      
        while(start <= n) {
            long end = start * 1000 - 1;
            long count = Math.min(n, end) - start + 1;

            if(count > 0 ) {
                ans += count*comma;
            }
            comma++;
            start *= 1000;
        }
        
     return ans;
    }
}