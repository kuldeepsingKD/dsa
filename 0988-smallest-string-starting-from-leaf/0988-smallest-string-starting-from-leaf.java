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
    String ans = " ";
   private void solve(TreeNode root,StringBuilder sb){
if(root==null) return;
sb.insert(0, (char)(root.val + 97));
if(root.left==null&&root.right==null)
if(ans.equals(" "))
ans=sb.toString();
else
ans=ans.compareTo(sb.toString())>0?sb.toString():ans;
solve(root.left,sb);
solve(root.right,sb);
sb.deleteCharAt(0);
}
    public String smallestFromLeaf(TreeNode root) {
        solve(root, new StringBuilder());
        return ans;
    }
}