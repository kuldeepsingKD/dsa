class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[n];

            for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        int ans = 0;

        for(int i=0; i<n; i++) {
            if(visited[i]) continue;

            Queue<Integer> que = new LinkedList<>();

            que.offer(i);
            visited[i] = true;

            int nodes = 0;
            int degreeSum = 0;

            while(!que.isEmpty()) {
                int curr = que.poll();

                nodes++;
                degreeSum += graph[curr].size();

                for(int next : graph[curr]) {
                    if(!visited[next]){
                        visited[next] = true;

                        que.offer(next);
                    }
                }
            } 

            int actualEdges = degreeSum/2;
            int exceptedEdges = nodes*(nodes-1)/2;

            if(actualEdges == exceptedEdges) {
                ans++;
            }      
        }

        return ans;
    }
}