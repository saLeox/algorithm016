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

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        generateParenthesis(0, 0, n, "");
        return res;
    }


    private void generateParenthesis(int left, int right, int n, String s) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }
        // 判断-减枝操作
        if (left < n) {
            generateParenthesis(left + 1, right, n, s + "(");
        }
        if (left > right) {
            generateParenthesis(left, right + 1, n, s + ")");
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
