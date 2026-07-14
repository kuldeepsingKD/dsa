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
    int maxDepth;
    int bottomLeft;
    private void dfs(TreeNode root, int currDepth) {
        if(root == null) return;

        if(currDepth > maxDepth){
            maxDepth = currDepth;
            bottomLeft = root.val;

        }

        dfs(root.left, currDepth+1);
        dfs(root.right, currDepth+1);
    }
    public int findBottomLeftValue(TreeNode root) {
        maxDepth = -1;
        dfs(root, 0);
        return bottomLeft;
    }
}