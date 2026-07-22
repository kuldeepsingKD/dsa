class Solution {
    private boolean possible(int x, int[] quantities, int n) {
        for(int product : quantities) {
            n -= (product + x - 1)/x;

            if(n < 0) {
                return false;
            }
        }

        return true;
    }
    public int minimizedMaximum(int n, int[] quantities) {
        int a = quantities.length;
        int l = 1, r = Arrays.stream(quantities).max().getAsInt();

        int result = 0;

        while(l <= r) {
            int mid = l + (r-l)/2;

            if(possible(mid, quantities, n)){
                result = mid;
                r = mid -1;
            }else{
                l = mid + 1;
            }
        }

        return result;
        
    }
}