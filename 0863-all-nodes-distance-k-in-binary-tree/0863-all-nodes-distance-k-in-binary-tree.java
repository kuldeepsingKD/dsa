/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<TreeNode,TreeNode> parent = new HashMap<>();
    private void buildParent(TreeNode root) {
        if(root == null) return;

        if(root.left != null){
            parent.put(root.left, root);
            buildParent(root.left);
        }


        if(root.right != null){
            parent.put(root.right, root);
            buildParent(root.right);
        }
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        buildParent(root);

        Queue<TreeNode> que = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        que.offer(target);
        visited.add(target);
        int distance = 0;

        while(!que.isEmpty()) {


            if(distance == k) break;

            int size = que.size();

            for(int i = 0; i < size; i++) {

                TreeNode node = que.poll();

                if(node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    que.offer(node.left);
                }

                if(node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    que.offer(node.right);
                }

                TreeNode par = parent.get(node);

                if(par != null && !visited.contains(par)){
                    visited.add(par);
                    que.offer(par);
                }
            }
            distance++;
        }

        List<Integer> ans = new ArrayList<>();

        while(!que.isEmpty()) {
            ans.add(que.poll().val);
        }

        return ans;

    }
}