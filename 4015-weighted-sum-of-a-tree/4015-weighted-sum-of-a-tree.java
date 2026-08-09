class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        int n = parent.length;
        
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i < n; i++) {
            adj.add(new ArrayList<>());

        }

        for(int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        long[] depth = new long[n];
        long h = 0;

        Queue<Integer> q = new LinkedList<>();

        q.offer(0);
        depth[0] = 1;

        while(!q.isEmpty()) {
            int curr = q.poll();
            h = Math.max(h, depth[curr]);

            for(int child : adj.get(curr)) {
                depth[child] = depth[curr] + 1;
                q.offer(child);
            }
        }

        long totalSum = 0;
        for(int i = 0; i < n; i++) {
            totalSum +=  nums[i] * (h - depth[i] + 1);
        }

        return totalSum;
    }
}