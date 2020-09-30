//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 696 👎 0


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
    // 中序遍历定位根节点，能够确定中序的左右子树区间，得到左右子树区间的长度，得到前序左右子树的区间
    // 前序遍历与中序遍历的区间长度是一致的

    private Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>(); // 一次遍历用以加速

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    // 前序:根左右
    // 中序:左根右
    private TreeNode buildTree(int[] preorder, int[] inorder, int preStr, int preEnd, int inStr, int inEnd) {
        // terminator
        TreeNode node = new TreeNode();
        if (preStr > preEnd || inStr > inEnd) {
            return null;
        }
        // process logic in current level
        int rootVal = preorder[preStr];
        node.val = rootVal;
        // 从中序获得根节点的位置 -> 获得左右子树的区间
        int inRootIndex = indexMap.get(rootVal);
        // 获得左右子树的区间长度
        // drill down
        node.left = buildTree(preorder, inorder, preStr + 1, preStr + (inRootIndex - inStr), inStr, inRootIndex - 1);
        node.right = buildTree(preorder, inorder, preStr + (inRootIndex - inStr) + 1, preEnd, inRootIndex + 1, inEnd);
        return node;
        // reverse
    }

}
//leetcode submit region end(Prohibit modification and deletion)
