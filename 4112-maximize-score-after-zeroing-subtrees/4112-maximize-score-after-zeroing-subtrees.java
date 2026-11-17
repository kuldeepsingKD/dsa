class Solution {

       private long dfs(int u, List<List<Integer>> adj, int[] op, int[] score) {
        long sum = score[u];
        long mn = Long.MAX_VALUE;

        for (int v : adj.get(u)) {
            long val = dfs(v, adj, op, score);
            sum += val;
            mn = Math.min(mn, val);
        }

        if (adj.get(u).isEmpty()) {
            return score[u];
        }

        if (op[u] == 1) {
            sum -= mn;
        } else {
            sum -= Math.min(0L, mn);
        }

        return sum;
    }

   

    public int maxScore(int[] parent, int[] op, int[] score) {
         int n = parent.length;
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            if (parent[i] != -1) {
                adj.get(parent[i]).add(i);
            }
        }

        return (int) dfs(0, adj, op, score);

    }
}