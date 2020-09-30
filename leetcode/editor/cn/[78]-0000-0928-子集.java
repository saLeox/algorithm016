//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法 
// 👍 813 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 2种解法
    // 1-递归, 当前结果加入或者不加入
    // 2-迭代, 遍历现有结果集, 把当前结果加进去

    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        //generateSubSet1(0, nums, new ArrayList<Integer>());
        generateSubSet(nums);
        return res;
    }

    // 1-递归, 当前结果加入或者不加入
    private void generateSubSet1(int index, int[] nums, List<Integer> subRes) {
        if (index == nums.length) {
            res.add(new ArrayList<Integer>(subRes)); //此处的subRes是全局共享的, 后面会被修改
            return;
        }
        int val = nums[index];
        generateSubSet1(index + 1, nums, subRes); // 选择不加
        subRes.add(val);
        generateSubSet1(index + 1, nums, subRes); // 选择加
        subRes.remove(subRes.size() - 1); // 恢复下探的状态, 此时已经执行完了, 此处的arr是全局共享的
    }

    // 2-迭代, 遍历现有结果集, 把当前结果加进去
    private void generateSubSet(int[] nums) {
        for (int num : nums) {
            if (res.size() == 0) {
                // 不加
                res.add(new ArrayList<Integer>());
                // 加
                List<Integer> arr = new ArrayList<Integer>();
                arr.add(num);
                res.add(arr);
            } else {
                List<List<Integer>> copy = new ArrayList<List<Integer>>(res);
                for (List<Integer> arr : copy) {
                    // 不加不用操作
                    List<Integer> newArr = new ArrayList<Integer>(arr);
                    newArr.add(num);
                    res.add(newArr);
                }
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
