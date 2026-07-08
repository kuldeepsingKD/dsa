class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        Map<Integer,Integer> childtoparent = new HashMap<>();
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int node = i;
            int leftChildNode = leftChild[i];
            int rightChildNode = rightChild[i];


             if (leftChildNode != -1) {
                adj.computeIfAbsent(node, k -> new ArrayList<>()).add(leftChildNode);

                if(childtoparent.containsKey(leftChildNode)){
                    return false;
                }else{
                    childtoparent.put(leftChildNode, node);
                }
        }

        if(rightChildNode != -1) {
            adj.computeIfAbsent(node, k -> new ArrayList<>()).add(rightChildNode);

            if(childtoparent.containsKey(rightChildNode)){
                return false;
            }else{
                childtoparent.put(rightChildNode, node);
            }
        }

    }

         int root = -1;

        for (int i = 0; i < n; i++) {
            if (!childtoparent.containsKey(i)) {
                if (root != -1) {
                    return false;
                } else {
                    root = i;
                }
            }
        }
        if (root == -1) {
            return false;
        }
            boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int count = 1;
        queue.add(root);
        visited[root] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child : adj.getOrDefault(node, Collections.emptyList())) {
                if (!visited[child]) {
                    visited[child] = true;
                    count++;
                    queue.add(child);
                }
            }
        }

        return count == n;
}
}