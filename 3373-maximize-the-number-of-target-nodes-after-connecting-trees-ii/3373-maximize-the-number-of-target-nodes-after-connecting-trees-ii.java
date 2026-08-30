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

    private void dfs(int curr, List<List<Integer>> adj, int parent, int[] mark, int[] count) {
        if(mark[curr] == 0) {
            count[0]++;
        }else{
            count[1]++;
        }

        for(int neigh : adj.get(curr)) {
            if(neigh != parent) {
                mark[neigh] = (mark[curr] == 0) ? 1 : 0;
                dfs(neigh, adj, curr, mark, count);
            }
        }
    }
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length+1;
        int m = edges2.length+1;

        List<List<Integer>> adj1 = buildGraph(n, edges1);
        List<List<Integer>> adj2 = buildGraph(m, edges2);

        int[] markA = new int[n];
        Arrays.fill(markA, -1);
        markA[0] = 0;
        int[] countA = new int[2];
        dfs(0, adj1, -1, markA, countA);

        int[] markB = new int[m];
        Arrays.fill(markB, -1);
        markB[0] = 0;
        int[] countB = new int[2];
        dfs(0, adj2, -1, markB, countB);

        int markFromTree2 = Math.max(countB[0], countB[1]);

        int[] ans = new int[n];

        for(int i = 0; i < n; i++) {
            ans[i] = (markA[i] == 0 ? countA[0] : countA[1]) + markFromTree2;
        } 


        return ans;



    }
}