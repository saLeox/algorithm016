//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 
// 👍 777 👎 0


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

    // 解法1: 利用中序遍历输出, 并缓存上一个访问的节点
    public boolean isValidBST(TreeNode root) {
        return inorderTraversalComparison(root);
    }

    private boolean inorderTraversalComparison(TreeNode node) {
        if (null == node) {
            return true;
        }
        if (!inorderTraversalComparison(node.left)) {
            return false;
        }
        int val = node.val;
        if (null != lastVal && val <= lastVal) {
            return false;
        }
        lastVal = val; // 刷新值
        if (!inorderTraversalComparison(node.right)) {
            return false;
        }
        return true;
    }
    // 比Integer.MIN_VALUE好, 因为极端情况会相等
    Integer lastVal = null;

    // 解法2: 搜索二叉树的每个节点有上下边界,并且可以传递给更下层的子节点
    public boolean isValidBST_1(TreeNode root) {
        return isValidBST_1(root, null, null);
    }
    private boolean isValidBST_1(TreeNode node, Integer lower, Integer upper) {
        if (null == node) {
            return true;
        }
        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }
        if (!isValidBST_1(node.left, lower, val)) {
            return false;
        }
        if (!isValidBST_1(node.right, val, upper)) {
            return false;
        }
        return true;
    }

    // 分别求左右的最值 这样每个节点不止经过1次
    public boolean isValidBST1(TreeNode root) {
        if (null == root) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left != null) {
            if (root.left.val >= root.val) {
                return false;
            }
            // 求左树的最大值
            if (getTreeMax(root.left) >= root.val) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.right.val <= root.val) {
                return false;
            }
            // 求右树的最小值
            if (getTreeMin(root.right) <= root.val) {
                return false;
            }
        }
        // 下潜
        return (isValidBST(root.left) && isValidBST(root.right));
    }

    private int getTreeMax(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        } else if (root.left != null && root.right == null) {
            return Math.max(root.val, getTreeMax(root.left));
        } else if (root.left == null && root.right != null) {
            return Math.max(root.val, getTreeMax(root.right));
        } else {
            return Math.max(root.val, Math.max(getTreeMax(root.left), getTreeMax(root.right)));
        }
    }

    private int getTreeMin(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        } else if (root.left != null && root.right == null) {
            return Math.min(root.val, getTreeMin(root.left));
        } else if (root.left == null && root.right != null) {
            return Math.min(root.val, getTreeMin(root.right));
        } else {
            return Math.min(root.val, Math.min(getTreeMin(root.left), getTreeMin(root.right)));
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
