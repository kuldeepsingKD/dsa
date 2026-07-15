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
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        boolean even_level = true;
        que.offer(root);
        int prev;
 

        while(!que.isEmpty()) {
            int n = que.size();

            
        if(even_level) {
            prev = Integer.MIN_VALUE;
        }else{
            prev = Integer.MAX_VALUE;
        }

        while(n-- >0){
            TreeNode node = que.poll();

            if(even_level && (node.val%2 == 0 || node.val <= prev)) {
                return false;
            }

              if(!even_level && (node.val%2 != 0 || node.val >= prev)) {
                return false;
            }

              prev = node.val;

        if(node.left != null) {
            que.offer(node.left);
        }

        if(node.right != null){
            que.offer(node.right);
        }
        }

       

        even_level = !even_level;
        }

        return true;
    }
}