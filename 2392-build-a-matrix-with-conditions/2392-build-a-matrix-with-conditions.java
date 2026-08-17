class Solution {
     private List<Integer> topoSort(int[][] edges, int n){
       List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        } 

        int[] indegree = new int[n+1];

        List<Integer> order = new ArrayList<>();

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> que = new LinkedList<>();
        int count = 0;
        for(int i = 1; i<=n; i++) {
            if(indegree[i] == 0) {
                que.offer(i);
                count++;
            }

        }

        while(!que.isEmpty()) {
            int u = que.poll();
            order.add(u);

            for(int v : graph.get(u)) {
                indegree[v]--;

                if(indegree[v] == 0) {
                    que.offer(v);
                    count++;
                }
            }
        }

        if(count != n) {
            return new ArrayList<>();
        }

        return order;
     }
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        List<Integer> orderRows = topoSort(rowConditions, k);
        List<Integer> orderCol = topoSort(colConditions, k);

        if(orderRows.isEmpty() || orderCol.isEmpty()) {
            return new int[][]{};
        }

        int[][]matrix = new int[k][k];
        Map<Integer, Integer> positionMap = new HashMap<>();

        for(int i=0; i<k; i++) {
            positionMap.put(orderCol.get(i), i);
        }

        for(int i=0; i<k; i++) {
            int element = orderRows.get(i);
            if(positionMap.containsKey(element)){
                matrix[i][positionMap.get(element)] = element;
            }
        }

        return matrix;
    }
}