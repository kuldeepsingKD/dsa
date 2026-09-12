class Solution {
    int n;
    int[][]t = new int[100001][3];
    public int binarySearch(int[][] events, int endTime) {
        int l = 0; 
        int r = n - 1;
        int result = n;

        while(l <= r) {
            int mid = l + (r-l)/2;

            if(events[mid][0] > endTime) {
                result = mid;
                r = mid -1;

            }else{
                l = mid + 1;
            }
        }

        return result;
    }

    public int solve(int[][] events, int i, int count) {
        if(count == 2 || i >= n) {
            return 0;
        }

        if(t[i][count] != -1) {
            return t[i][count];
        }

        int next = binarySearch(events, events[i][1]);
        int take = events[i][2] + solve(events, next, count+1);

        int not_take = solve(events, i+1, count);

        return t[i][count] = Math.max(take, not_take);
    }
    public int maxTwoEvents(int[][] events) {
        n = events.length;
          Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

           for (int[] row : t) {
            Arrays.fill(row, -1);
        }


          int count = 0;
          return solve(events, 0, count);


    }
}