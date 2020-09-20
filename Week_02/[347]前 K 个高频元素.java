//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 520 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 用大根堆存自定义的node节点
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        // 先用哈希存频次
        HashMap<Integer, Integer> freq = new HashMap();
        for (int num : nums) {
            if (freq.containsKey(num)) {
                freq.put(num, freq.get(num) + 1);
            } else {
                freq.put(num, 1);
            }
        }
        // 用大根堆存储, 输出前k
        PriorityQueue<Node> heap = new PriorityQueue<Node>((v1, v2) -> v2.freq - v1.freq);
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            Node node = new Node();
            node.val = entry.getKey();
            node.freq = entry.getValue();
            heap.add(node);
        }
        // 输出
        for (int i = 0; i < k; i++) {
            Node node = heap.poll();
            res[i] = node.val;
        }
        return res;
    }

    class Node{
        int val;
        int freq;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
