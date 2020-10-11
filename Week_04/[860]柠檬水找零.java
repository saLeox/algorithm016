//在柠檬水摊上，每一杯柠檬水的售价为 5 美元。 
//
// 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。 
//
// 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。 
//
// 注意，一开始你手头没有任何零钱。 
//
// 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。 
//
// 示例 1： 
//
// 输入：[5,5,5,10,20]
//输出：true
//解释：
//前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
//第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
//第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
//由于所有客户都得到了正确的找零，所以我们输出 true。
// 
//
// 示例 2： 
//
// 输入：[5,5,10]
//输出：true
// 
//
// 示例 3： 
//
// 输入：[10,10]
//输出：false
// 
//
// 示例 4： 
//
// 输入：[5,5,10,10,20]
//输出：false
//解释：
//前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
//对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
//对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
//由于不是每位顾客都得到了正确的找零，所以答案是 false。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= bills.length <= 10000 
// bills[i] 不是 5 就是 10 或是 20 
// 
// Related Topics 贪心算法 
// 👍 132 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean lemonadeChange(int[] bills) {
        // 20块不需要关心，因为不存在需要找20的场景
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five = five - 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean lemonadeChange1(int[] bills) {
        int remain = 0;
        int[] arr = new int[]{0, 0, 0}; // 分别存放 5,10,20的金额
        for (int i = 0; i < bills.length; i++) {
            int num = bills[i] - 5; // 需要找钱的金额
            if (num > remain) {
                return false;
            }
            // 用贪心算法计算能否找零，不能则返回false
            if (num > 0) {
                if (!returnMoney(arr, num)) {
                    return false;
                }
            }
            // 收钱
            remain = remain + 5;
            if (bills[i] == 5) {
                arr[0] = arr[0] + 1;
            } else if (bills[i] == 10) {
                arr[1] = arr[1] + 1;
            } else if (bills[i] == 20) {
                arr[2] = arr[2] + 1;
            }
        }
        return true;
    }

    private boolean returnMoney(int[] arr, int num) {
        boolean res = false;
        int[] tmp = new int[]{arr[0], arr[1], arr[2]};
        while (num > 0 && (tmp[0] > 0 || tmp[1] > 0 || tmp[2] > 0)) {
            if (tmp[2] > 0 && num >= 20) {
                num = num - 20;
                tmp[2] = tmp[2] - 1;
            } else if (tmp[1] > 0 && num >= 10) {
                num = num - 10;
                tmp[1] = tmp[1] - 1;
            } else if (tmp[0] > 0 && num >= 5) {
                num = num - 5;
                tmp[0] = tmp[0] - 1;
            } else {
                break;
            }
        }
        if (num == 0) {
            res = true;
            // 可以改值, 但是绝对不能改引用
            arr[0] = tmp[0];
            arr[1] = tmp[1];
            arr[2] = tmp[2];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
