//您需要在二叉树的每一行中找到最大的值。 
//
// 示例： 
//
// 
//输入: 
//
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
//
//输出: [1, 3, 9]
// 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 93 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    List<Integer> res = new ArrayList<Integer>();

    // DFS 解法
    // 效率会优于BFS
    public List<Integer> largestValues(TreeNode root) {
        dfs(root, 1);
        return res;
    }

    private void dfs(TreeNode root, int level) {
        if (null == root) {
            return;
        }
        // 此处无法new对象，所有要进行数值上的判断
        if (res.size() + 1 == level) {
            res.add(root.val);
        } else {
            int existVal = res.get(level - 1);
            if (existVal < root.val) {
                res.set(level - 1, root.val);
            }
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    // BFS 解法
    public List<Integer> largestValues1(TreeNode root) {
        if (null == root) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>(); // 注意queue的类型，是节点，不是整型
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Integer max = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (null == max || (null != max && node.val > max.intValue())) {
                    max = node.val;
                }
                if (null != node.left) {
                    q.offer(node.left);
                }
                if (null != node.right) {
                    q.offer(node.right);
                }
            }
            if (null != max) {
                res.add(max);
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
