//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 403 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine(int n, int k) {
        combine(n, k, new ArrayList<Integer>(), 1);
        return res;
    }

    private void combine(int n, int k, List<Integer> arr, int cur) {
        if (arr.size() == k) {
            res.add(new ArrayList<Integer>(arr));
            return;
        }
        for (int i = cur; i <= n; i++) {
            arr.add(i);
            //是i+1,不是cur+1
            combine(n, k, arr, i + 1);
            arr.remove(arr.size() - 1);
        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)
