/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if(root == null) return ans;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        while(!que.isEmpty()) {
            int max = Integer.MIN_VALUE;

            int size = que.size();

            for(int i=0; i< size; i++) {
                TreeNode node = que.poll();

                max = Math.max(max, node.val);

                if(node.left != null) {
                    que.offer(node.left);
                }

                if(node.right != null) {
                    que.offer(node.right);
                }
            }

            ans.add(max);
        }

        return ans;


    }
}