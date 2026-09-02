class Solution {
    private static class Pair {
        int node, weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

     private boolean canAllReachZero(int n, Map<Integer, List<Pair>> adj, int mid) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        // Node 0 se traversal shuru karenge (reversed graph mein)
        queue.add(0);
        visited[0] = true;
        int visitedCount = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // getOrDefault() use karne se NullPointerException nahi aayega
            for (Pair neighbor : adj.getOrDefault(curr, new ArrayList<>())) {
                // Sirf wahi edges lenge jinka weight <= mid ho
                if (!visited[neighbor.node] && neighbor.weight <= mid) {
                    visited[neighbor.node] = true;
                    queue.add(neighbor.node);
                    visitedCount++;
                }
            }
        }

        // Agar saare (n) nodes visit ho gaye, matlab sab 0 tak pahunch sakte hain
        return visitedCount == n;
    }
    public int minMaxWeight(int n, int[][] edges, int threshold) {
          Map<Integer, List<Pair>> adj = new HashMap<>();
        int maxWt = 0;

        // Form the adjacency list with reversed edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair(u, w)); // reversed edge
            maxWt = Math.max(maxWt, w);
        }

        int result =  -1;

        int l = 1;
        int r = maxWt;

        while(l <= r) {
            int mid = l+(r-l)/2;

            if(canAllReachZero(n, adj, mid)) {
                result = mid;
                r = mid-1;
            }else{
                l = mid+1;
            }
        }

        return result;  
    }
}