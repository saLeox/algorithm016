//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10 ^ 4 
// 0 <= prices[i] <= 10 ^ 4 
// 
// Related Topics 贪心算法 数组 
// 👍 876 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 贪心算法：存在正的价格差则为有效交易
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i] - prices[i-1];
            if(tmp > 0) profit += tmp;
        }
        return profit;
    }

    // 贪心算法: 只要下一天比当天高 则买入, 低则买入
    public int maxProfit1(int[] prices) {
        int profit = 0;
        Integer cur_share = null;
        for (int i = 0; i < prices.length; i++) {
            // sell first then sell
            // sell out condition
            if (i > 0 && cur_share != null) {
                if (prices[i] > prices[i - 1] || i == prices.length - 1) {
                    profit += prices[i] - cur_share;
                    cur_share = null; //归零
                    // System.out.println("sell at:" + prices[i]);
                }
            }
            // buy in condition
            if (i < prices.length - 1 && cur_share == null) {
                if (prices[i + 1] > prices[i]) {
                    cur_share = prices[i];
                    // System.out.println("buy at:" + prices[i]);
                }
            }
        }
        return profit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
