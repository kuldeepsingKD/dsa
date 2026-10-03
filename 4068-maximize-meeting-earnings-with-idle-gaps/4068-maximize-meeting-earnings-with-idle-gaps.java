class Solution {
    class Meeting{
        int start;
        int end;
        int revenue;

        Meeting(int start, int end, int revenue){
            this.start = start;
            this.end = end;
            this.revenue = revenue;
        }
    }

    class State{
        int end;
        long earning;

        State(int end, long earning){
            this.end = end;
            this.earning = earning;
        }
    }

    public long maxEarnings(int[][] meetings) {
        int n = meetings.length;

        Meeting[] arr = new Meeting[n];

        for(int i=0;i<n;i++){
            arr[i] = new Meeting(meetings[i][0], meetings[i][1], meetings[i][2]);
        }

        Arrays.sort(arr, (a, b) -> Integer. compare(a.start, b.start));

        PriorityQueue<State> pq = new PriorityQueue<>((a, b)->Integer. compare(a.end, b.end));

        long best = Long.MIN_VALUE;
        long ans = 0;

        for(Meeting cur:arr){
            while(!pq.isEmpty() && pq.peek().end <= cur.start){
                State state = pq.poll();
                best = Math.max(best, state.earning-state.end);
            }

            long curEarning = cur.revenue;

            if(best != Long.MIN_VALUE){
                curEarning = Math.max(curEarning, curEarning + cur.start + best);
            }

            ans = Math.max(ans, curEarning);

            pq.offer(new State(cur.end, curEarning));
        }
        return ans;
    }
}