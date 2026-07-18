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
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int levelSum = root.val;

        while(!que.isEmpty()){
            int nextLevelSum = 0;
            int n = que.size();

            while(n-- > 0) {
                TreeNode curr = que.poll();

                curr.val = levelSum - curr.val;
                int siblingSum = (curr.left != null ? curr.left.val : 0);
                siblingSum += (curr.right != null ? curr.right.val : 0);

                if(curr.left != null) {
                    nextLevelSum += curr.left.val;
                    curr.left.val = siblingSum;
                    que.offer(curr.left);
                }

                 if(curr.right != null) {
                    nextLevelSum += curr.right.val;
                    curr.right.val = siblingSum;
                    que.offer(curr.right);
                }



            }

            levelSum = nextLevelSum;
        }

        return root;
    }
}