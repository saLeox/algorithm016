//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 数学 
// 👍 72 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int[] uglyNumber = {2,3,5};
    // 丑数是从小大大输出的，因此用小根堆存储丑数
    // 每次poll出最小的那个，count累加直至达到n
    // 同时不断往小根堆中添加新的丑数
    public int nthUglyNumber(int n) {
        // 每一个丑数都是已存在丑数乘以2/3/5得到的
        // 一边添加 一边poll出最小丑数
        PriorityQueue<Long> queue = new PriorityQueue();
        queue.add(1L);
        int count = 0;
        while (!queue.isEmpty()) {
            long cur = queue.poll();
            if (++count >= n) {
                return (int)cur;
            }
            for (int num : uglyNumber) {
                long tmp = num * cur;
                if (!queue.contains(tmp)) {
                    queue.add(tmp);
                }
            }
        }
        return -1;
    }

    // 不应该通过++得到丑数, 中间过程太长
    public int nthUglyNumber1(int n) {
        if (n <= 5) {
            return n;
        }
        HashSet<Float> set = new HashSet();
        set.add(2.0f);
        set.add(3.0f);
        set.add(4.0f);
        set.add(5.0f);
        int prev = 5;
        for (int i = 6; i <= n; i++) {
            while (!(set.contains(++prev / 2.0f)
                    || set.contains(prev / 3.0f)
                    || set.contains(prev / 5.0f))) {
            }
            set.add(prev * 1.0f);
        }
        return prev;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
