class Solution {
    private boolean isPossible(long target, int m, int[] points, int n) {
        long moves = 0;
        long advanceToNext = 0;
        long normalMoves = 0;

        for(int i = 0; i < n && moves <= m; i++) {
            int gamepoint = points[i];
            long games = (target + gamepoint -1)/gamepoint;

            if(advanceToNext >= games) {
                advanceToNext = 0;
                normalMoves += 1;
            }else{
                long pointsAlreadyCovered = advanceToNext * gamepoint;
                games = (target - pointsAlreadyCovered + gamepoint - 1)/gamepoint;

                moves += (2*games) - 1;

                advanceToNext = Math.max(games-1, 0);
                moves += normalMoves;
                normalMoves = 0;

            }
        }

       return moves <= m;
    }
    public long maxScore(int[] points, int m) {
        int n = points.length;

        long result = 0;
        long l = 1, r = (long)1e15;

        while(l <= r) {
            long mid = l + (r-l)/2;

            if(isPossible(mid, m, points, n)){
               result = mid;
               l = mid+1;
            }else {
                r = mid-1;
            }
        }
        return result;
    }
}