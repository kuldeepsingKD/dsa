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
    public int maxLevelSum(TreeNode root) {

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        int ans = 1;
        int level = 1;
        int maxSum = Integer.MIN_VALUE;

        while(!que.isEmpty()){
            int sum = 0;

            int size = que.size();

            for(int i = 0; i < size; i++) {
                TreeNode node = que.poll();

                sum += node.val;

                if(node.left != null){
                    que.offer(node.left);
                }

                if(node.right != null) {
                    que.offer(node.right);
                }
            }

            
            
            if (sum > maxSum) {
                maxSum = sum;
                ans = level;
            }
            level++;
        }

        return ans;

        
    }
}