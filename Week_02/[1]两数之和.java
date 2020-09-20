//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 9102 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 加上判断就不需要考虑多个位置的问题了
    // map.containsKey(gap) && map.get(gap) != i
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int gap = target - nums[i];
            if (map.containsKey(gap) && map.get(gap) != i) {
                result[0] = i;
                result[1] = map.get(gap);
                return result;
            }
        }
        return result;
    }


    // a+b=c --> a=c-b, 先将c-b全部存进hashMap, 及其b的位置
    public int[] twoSum_his1(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, List<Integer>> indexMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int gap = target - nums[i];
            if (indexMap.containsKey(gap)) {
                indexMap.get(gap).add(i);
            } else {
                List<Integer> arr = new ArrayList<Integer>();
                arr.add(i);
                indexMap.put(gap, arr);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (indexMap.containsKey(nums[i])) {
                for (int j : indexMap.get(nums[i])) {
                    if (i != j) {
                        result[0] = i;
                        result[1] = j;
                        return result;
                    }
                }
            }
        }

        return result;
    }


    // 数组排序后向中间夹逼
    // 开辟哈希表存放原始元素的位置(消耗较多内存)
    public int[] twoSum_his2(int[] nums, int target) {
        Map<Integer, List<Integer>> indexMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (indexMap.containsKey(nums[i])) {
                List<Integer> arr = indexMap.get(nums[i]);
                arr.add(i);
                indexMap.put(nums[i], arr);
            } else {
                List<Integer> arr = new ArrayList<Integer>();
                arr.add(i);
                indexMap.put(nums[i], arr);
            }
        }
        Arrays.sort(nums);
        int[] result = new int[2];
        int i = 0;
        int j = nums.length - 1;
        while (j > i) {
            // 排序后向中间夹逼
            if (target == nums[i] + nums[j]) {
                result[0] = indexMap.get(nums[i]).get(0);
                if (nums[i] == nums[j]) {
                    result[1] = indexMap.get(nums[i]).get(1);
                } else {
                    result[1] = indexMap.get(nums[j]).get(0);
                }
                break;
            } else if (target > nums[i] + nums[j]) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
