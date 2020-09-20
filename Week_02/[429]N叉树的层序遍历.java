//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 109 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/


class Solution {
    // 队列
    //
    // 各层遍历过程中，队列大小提前取出，因为处于动态变化当中
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size()>0){
            List<Integer> num = new ArrayList();
            int size = queue.size();
            for (int i=0; i<size; i++){
                Node node = queue.poll();
                num.add(node.val);
                queue.addAll(node.children);
            }
            result.add(num);
        }
        return result;
    }


    // 依次存入各层的节点
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (null == root) {
            return res;
        }
        List<Node> nextArr = new ArrayList<Node>();
        List<Node> tmpArr = new ArrayList<Node>();
        nextArr.add(root);
        while (null != nextArr && nextArr.size() > 0) {
            tmpArr.clear();
            List<Integer> numArr = new ArrayList<Integer>();
            for (Node node : nextArr) {
                numArr.add(node.val);
                tmpArr.addAll(node.children);
            }
            res.add(numArr);
            nextArr.clear();
            nextArr.addAll(tmpArr);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
