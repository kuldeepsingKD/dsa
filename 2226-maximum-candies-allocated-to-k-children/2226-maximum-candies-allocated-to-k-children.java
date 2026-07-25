class Solution {
    private boolean canPossible(int[] candies, int mid, long k) {
        int n = candies.length;
        long count = 0;

        for(int i =0 ; i < n; i++) {
            count += candies[i]/mid;

            if(count >= k) {
                return true;
            }
        }

        return count >= k;
    }
    public int maximumCandies(int[] candies, long k) {
        int maxC = 0;
        int n = candies.length;
        long total = 0;

        for(int i = 0; i < n; i++) {
            total += candies[i];
            maxC = Math.max(maxC, candies[i]);
        }

        if(total  < k) {
            return 0;
        }

        int result = 0;
        int l = 1;
        int r = maxC;

        while(l <= r) {
            int mid = l + (r - l)/2;

            if(canPossible(candies, mid, k)) {
                result = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return result;

    }
}