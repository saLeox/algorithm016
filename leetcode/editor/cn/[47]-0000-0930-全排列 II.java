//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法 
// 👍 474 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); // 先排序
        permuteUnique(nums, 0, new ArrayList<Integer>(), new boolean[nums.length]);
        return res;
    }

    private void permuteUnique(int[] nums, int index, List<Integer> arr, boolean[] uesd) {
        if (index == nums.length) {
            res.add(new ArrayList<Integer>(arr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (uesd[i] || (i > 0 && nums[i] == nums[i - 1] && !uesd[i - 1])) {
                continue;
            }
            // process
            arr.add(nums[i]);
            uesd[i] = true;
            permuteUnique(nums, index + 1, arr, uesd);
            // reverse status
            arr.remove(arr.size() - 1);
            uesd[i] = false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
