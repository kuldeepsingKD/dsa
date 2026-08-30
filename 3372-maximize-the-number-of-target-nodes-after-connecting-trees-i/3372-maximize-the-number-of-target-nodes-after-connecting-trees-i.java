class Solution {
      private List<List<Integer>> buildGraph(int size, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return adj;
    }

    private int bfs(int startNode, int maxDistance, List<List<Integer>> adj, int totalNodes) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[totalNodes];

        que.offer(startNode);
        visited[startNode] = true;

        int count = 0;
        int currentDistance = 0;

        while(!que.isEmpty() && currentDistance <= maxDistance) {
            int levelSize = que.size();

            for(int i = 0; i < levelSize; i++) {
                int curr = que.poll();
                count++;

                for(int neighbour : adj.get(curr)) {
                    if(!visited[neighbour]) {
                        visited[neighbour] = true;
                        que.add(neighbour);
                    }
                }
            }

            currentDistance++;
        }
        return count;
    }
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length+1;
        int m = edges2.length+1;

        List<List<Integer>> adj1 = buildGraph(n, edges1);
        List<List<Integer>> adj2 = buildGraph(m, edges2);

        int maxTree2Count = 0;
        
        if(k > 0) {
            for(int i = 0; i < m; i++) {
                int count = bfs(i, k-1, adj2, m);
                maxTree2Count = Math.max(maxTree2Count, count);
            }
        }

        int[] ans = new int[n];

        for(int i = 0; i < n; i++) {
            int tree1Count = bfs(i, k, adj1, n);
            ans[i] = tree1Count + maxTree2Count;
        }

        return ans;
    }
}