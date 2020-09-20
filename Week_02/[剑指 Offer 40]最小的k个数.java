//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法 
// 👍 134 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 利用堆实现TOPK问题
// PriorityQueue<>默认支持小根堆
// 大根堆需要使用lambda表达式重写 - new PriorityQueue<>((v1, v2) -> v2 - v1);
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        if (k > arr.length) {
            return res;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((v1, v2) -> v1 - v2);
        for (int i : arr) {
            heap.add(i);
        }
        for (int i = 0; i <= k - 1; i++) {
            res[i] = heap.poll();
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
