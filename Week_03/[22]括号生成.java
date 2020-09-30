//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1323 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(res, "", n, n);
        return res;
    }

    private void generateParenthesis(List<String> res, String s, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) {
            this.generateParenthesis(res, s + "(", left - 1, right);
        }
        if (right > 0 && right > left) {
            this.generateParenthesis(res, s + ")", left, right - 1);
        }
    }

    // 错误方法 n=4 时, (())(())无法生成
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<String>();
        if (n == 1) {
            res.add("()");
        } else {
            List<String> tmp = new ArrayList<String>();
            res = generateParenthesis(n - 1);
            for (String str : res) {
                if (!tmp.contains("(" + str + ")")) {
                    tmp.add("(" + str + ")");
                }
            }
            for (String str : res) {
                if (!tmp.contains("()" + str)) {
                    tmp.add("()" + str);
                }
            }
            for (String str : res) {
                if (!tmp.contains(str + "()")) {
                    tmp.add(str + "()");
                }
            }
            res = tmp;
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
