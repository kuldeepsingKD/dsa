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
    private boolean inOrder(TreeNode root, int sum, int targetSum){
        if(root == null){
            return false;
        }

        sum += root.val;

         if (root.left == null && root.right == null) {
            return sum == targetSum;
        }

        boolean leftSide = inOrder(root.left, sum, targetSum);
        boolean rightSide = inOrder(root.right, sum, targetSum);

        return leftSide || rightSide;
        
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        int sum = 0;
        boolean result = inOrder(root, sum, targetSum);
        return result; 
    }
}