//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 
// 👍 654 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    List<List<Integer>> res = new ArrayList<>();

    // BFS - 迭代：将queue中一个层级的节点，一次性消费掉
    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> arr = new ArrayList<Integer>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node == null) {
                    continue;
                }
                arr.add(node.val);
                // 左右子树可能存在为空的情况
                q.offer(node.left);
                q.offer(node.right);
            }
            if (arr.size() > 0) {
                res.add(arr);
            }
        }
        return res;
    }

    // DFS - 递归：将level传递给子节点, 没有reverse操作
    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 1);
        return res;
    }
    private void dfs(TreeNode root, int level) {
        if (null == root) {
            return;
        }
        if (level > res.size()) {
            List<Integer> arr = new ArrayList<Integer>();
            res.add(arr);
        }
        res.get(level - 1).add(root.val);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }


}
//leetcode submit region end(Prohibit modification and deletion)
