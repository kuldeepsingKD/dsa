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
      private List<Integer> dfs(TreeNode node, int distance) {

        List<Integer> list = new ArrayList<>();

        if (node == null)
            return list;

        // Leaf node
        if (node.left == null && node.right == null) {
            list.add(1);
            return list;
        }

        List<Integer> left = dfs(node.left, distance);
        List<Integer> right = dfs(node.right, distance);

        // Count good pairs
        for (int l : left) {
            for (int r : right) {
                if (l + r <= distance)
                    ans++;
            }
        }

        // Return distances to parent (+1)
        for (int l : left) {
            if (l + 1 <= distance)
                list.add(l + 1);
        }

        for (int r : right) {
            if (r + 1 <= distance)
                list.add(r + 1);
        }

        return list;
    }
    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return ans;
    }
}