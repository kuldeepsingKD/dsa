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
        Map<String, Integer> map = new HashMap<>();
    List<TreeNode> ans = new ArrayList<>();
     private String dfs(TreeNode root) {

        if (root == null) {
            return "#";
        }

        String left = dfs(root.left);
        String right = dfs(root.right);

        String serial = root.val + "," + left + "," + right;

        map.put(serial, map.getOrDefault(serial, 0) + 1);

        if (map.get(serial) == 2) {
            ans.add(root);
        }

        return serial;
    }
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }
}