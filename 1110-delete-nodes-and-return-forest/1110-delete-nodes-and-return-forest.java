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
      private TreeNode deletehelper(TreeNode root, Set<Integer> todelete, List<TreeNode> result) {
        if (root == null) {
            return null;
        }

        root.left = deletehelper(root.left, todelete, result);
        root.right = deletehelper(root.right, todelete, result);

        if (todelete.contains(root.val)) {
            if (root.left != null) {
                result.add(root.left);
            }
            if (root.right != null) {
                result.add(root.right);
            }
            return null;
        } else {
            return root;
        }
    }
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> todelete = new HashSet<>();

        for(int num : to_delete){
            todelete.add(num);
        }

        deletehelper(root, todelete, result);

        if(!todelete.contains(root.val)){
            result.add(root);
        }

        return result;
    }
}