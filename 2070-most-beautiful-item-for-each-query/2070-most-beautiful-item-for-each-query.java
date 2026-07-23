class Solution {
    private int BinarySearch(int[][] items, int queryPrice) {
        int l = 0, r = items.length - 1;

        int maxBeauty = 0,  mid ;
        while(l <= r) {
            mid = l + (r-l)/2;
            if(items[mid][0] > queryPrice){
                r = mid - 1;
            }else{
                maxBeauty = Math.max(maxBeauty, items[mid][1]);
                l = mid+1;
            }
        }
        return maxBeauty;
    }
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int n = items.length;
        int m = queries.length;

        int[] result = new int[m];

        Arrays.sort(items, (a,b) -> Integer.compare(a[0], b[0]));

        int maxBeautySeen = items[0][1];

        for(int i=1; i<n; i++) {
            maxBeautySeen = Math.max(maxBeautySeen, items[i][1]);
            items[i][1] = maxBeautySeen;
        }

        for(int i=0; i<m; i++) {
            int queryPrice = queries[i];
            result[i] = BinarySearch(items,queryPrice);
        }

        return result;
    }
}