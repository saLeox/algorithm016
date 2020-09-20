//给定一个二叉树，返回它的中序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 713 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
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
import java.util.*;

class Solution {
    List<Integer> arr = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    // 迭代
    // 深度优先策略-先到达最左侧的节点
    // 用 root表示当前节点 --避免往左边一直深入导致无法回撤的情况
    // root不为空时入栈，再不断指向左节点
    // 被指向的左节点不存在时，pop栈顶元素，root指向其右节点
    public List<Integer> inorderTraversal(TreeNode root) {
        while (!stack.empty() || null != root) {
            if (null != root) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                arr.add(node.val);
                root = node.right;
            }
        }
        return arr;
    }

    // 递归
    public List<Integer> inorderTraversal1(TreeNode root) {
        if(null == root){
            return arr;
        }
        if(null != root.left){
            inorderTraversal(root.left);
        }
        arr.add(root.val);
        if(null != root.right){
            inorderTraversal(root.right);
        }
        return arr;
    }
}



//leetcode submit region end(Prohibit modification and deletion)
