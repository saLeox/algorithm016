//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针 
// 👍 2572 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 数组进组排序
    // a+b+c=0 转换为 a+b=-c
    // -c范围[0,length-2)
    // a和b介于区间两侧进行夹逼
    // a和b主要要与邻接值判断重
    // 停止条件为a和b相交
    // 返回所有可能的三元组集合
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int c = 0; c < nums.length - 2; c++) {
            // c>0之后 在右侧的a和b不可能相加得负
            if (nums[c] > 0) {
                break;
            }
            // c不能与此前的重复
            if (c > 0 && nums[c] == nums[c - 1]) {
                continue;
            }
            int a = c + 1;
            int b = nums.length - 1;
            while (b > a) {
                if (-nums[c] == nums[a] + nums[b]) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[c]);
                    list.add(nums[a]);
                    list.add(nums[b]);
                    result.add(list);
                    // 下列方式虽然简洁,但是耗时较多
                    //result.add(new ArrayList(Arrays.asList(nums[c], nums[a], nums[b])));
                    // 此时指针需要继续移动,且不等于原来的数
                    while (b > a && nums[a] == nums[++a]) ;
                    while (b > a && nums[b] == nums[--b]) ;
                } else if (-nums[c] > nums[a] + nums[b]) {
                    while (b > a && nums[a] == nums[++a]) ;
                } else {
                    while (b > a && nums[b] == nums[--b]) ;
                }
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
