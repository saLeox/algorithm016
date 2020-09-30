//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法 
// 👍 748 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int majorityElement(int[] nums) {
        return majorityElement(nums, 0, nums.length - 1);
    }

    private int majorityElement(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) / 2;
        int leftRes = majorityElement(nums, left, mid);
        int rightRes = majorityElement(nums, mid + 1, right);
        if (leftRes == rightRes) {
            return leftRes;
        }
        int leftTimes = count(nums, leftRes, left, right);
        int rightTimes = count(nums, rightRes, left, right);
        return (leftTimes > rightTimes) ? leftRes : rightRes;
    }

    private int count(int[] nums, int target, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == target) {
                count++;
            }
        }
        return count;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
