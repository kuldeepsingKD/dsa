class Solution {
    public long[] earliestActivationTimes(int n, int[][] edges, int[] requiredSignals) {
       List<List<long[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new long[]{edge[1], edge[2]});
        }

        long[] ans = new long[n];
        Arrays.fill(ans, -1L);

        // Har node ke distinct incoming neighbors track karne ke liye
        Set<Integer>[] receivedFrom = new HashSet[n];
        for (int i = 0; i < n; i++) {
            receivedFrom[i] = new HashSet<>();
        }

        // Min-Heap: {arrivalTime, targetNode, senderNode}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        // Node 0 time 0 par active hota hai (sender = -1)
        pq.offer(new long[]{0L, 0L, -1L});

        // Edge case: Jin nodes ko 0 signals chahiye wo time 0 par hi active honge
        for (int i = 1; i < n; i++) {
            if (requiredSignals[i] == 0) {
                pq.offer(new long[]{0L, i, -1L});
            }
        }

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long time = curr[0];
            int u = (int) curr[1];
            int sender = (int) curr[2];

            // Agar u pehle hi active ho chuka hai, toh ignore karein
            if (ans[u] != -1L) continue;

            // Agar kisi valid neighbor ne signal bheja hai, toh usko record karein
            if (sender != -1) {
                receivedFrom[u].add(sender);
            }

            // Check karein kya u ke activation ke liye zaroori signals poore ho gaye hain
            if (u == 0 || receivedFrom[u].size() >= requiredSignals[u]) {
                ans[u] = time;

                // Ab node u apne saare outgoing neighbors ko signal bhejega
                for (long[] edge : adj.get(u)) {
                    int v = (int) edge[0];
                    long delay = edge[1];
                    
                    if (ans[v] == -1L) {
                        pq.offer(new long[]{time + delay, v, u});
                    }
                }
            }
        }

        return ans;
    }
}