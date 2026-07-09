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
    int ans = 0;

    private void dfs(TreeNode node, int left, int right) {
        if(node == null) return ;


        ans = Math.max(ans, Math.max(left, right));

        dfs(node.left, right+1, 0);
        dfs(node.right, 0, left+1);
    }

    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        return ans;
    }
}