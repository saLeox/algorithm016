//给定一个二叉树，返回它的 前序 遍历。 
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
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 367 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<Integer> arr = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    // 迭代
    // 从顶层节点开始
    // 不需要利用当前节点
    // 中间节点只有在开始时提前入栈
    // 右节点先入栈 左节点后入栈
    public List<Integer> preorderTraversal(TreeNode root) {
        if(null == root){
            return arr;
        }
        stack.push(root);
        while (!stack.empty()){
            TreeNode node = stack.pop();
            arr.add(node.val);
            if(null != node.right){
                stack.push(node.right);
            }
            if(null != node.left){
                stack.push(node.left);
            }
        }
        return arr;
    }



    public List<Integer> preorderTraversal1(TreeNode root) {
        if(null == root){
            return arr;
        }
        arr.add(root.val);
        if(null != root.left){
            preorderTraversal(root.left);
        }
        if(null != root.right){
            preorderTraversal(root.right);
        }
        return arr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
