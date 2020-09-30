//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 922 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        // HashSet<Integer> set = new HashSet<Integer>();
        permuteHelper(nums, 0, new ArrayList<Integer>(), used);
        return res;
    }

    public void permuteHelper(int[] nums, int n, List<Integer> arr, boolean[] used) {
        // terminator
        if (n == nums.length) {
            res.add(new ArrayList<Integer>(arr)); //另存给新数组
            return;
        }
        // process logic in current level
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (used[i]) {
                continue;
            }
            arr.add(val);
            used[i] = true;
            // drill down
            permuteHelper(nums, n + 1, arr, used);
            // reverse status
            arr.remove(arr.size() - 1);
            used[i] = false;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
