class Solution {
    private int count(long x, int m, int n) {
        int cnt = 0;
        for(int i=1; i <= m; i++) {
            cnt += Math.min(n, (int)(x/i));
        }

        return cnt;
    }
    public int findKthNumber(int m, int n, int k) {
        long l = 1;
        long r = (long)m*n;

        while(l < r) {
            long mid = l + (r-l)/2;
            if(count(mid, m, n) >= k) {
                r = mid;
            }else{
                l = mid+1;
            }
        }

        return (int)l;
    }
}