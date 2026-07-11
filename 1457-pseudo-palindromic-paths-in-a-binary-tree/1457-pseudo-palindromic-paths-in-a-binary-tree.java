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
    int result = 0;
    private void solve(TreeNode root, int[] count) {
        if(root == null) return;
        

        count[root.val]++;

        if(root.left == null && root.right == null) {
            int oddFreq = 0;
            for(int i=1; i <= 9; i++) {
                if(count[i]%2 != 0){
                    oddFreq++;
                }
            }

            if(oddFreq <= 1){
                result += 1;
            }


        }

        solve(root.left, count);
        solve(root.right, count);

         count[root.val]--;


    }
    public int pseudoPalindromicPaths (TreeNode root) {
        int[] count = new int[10];

        solve(root, count);
        return result;


    }
}