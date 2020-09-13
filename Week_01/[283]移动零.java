//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 731 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        // 快指针带动慢指针更新(++操作)
        // 遇到特定情况, 快指针跳过, 慢指针停止更新
        int j = 0; // 记录0的慢指针
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 快慢指针不同步则交换值
                if (i != j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++; //快指针控制慢指针
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)