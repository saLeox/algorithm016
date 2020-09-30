//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法 
// 👍 935 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> res = new ArrayList<String>();
    HashMap<Integer, String> map = new HashMap<Integer, String>() {{
        put(2, "abc");
        put(3, "def");
        put(4, "ghi");
        put(5, "jkl");
        put(6, "mno");
        put(7, "pqrs");
        put(8, "tuv");
        put(9, "wxyz");
    }};


    public List<String> letterCombinations(String digits) {
        if (null == digits || digits.length() == 0) {
            return res;
        }
        letterCombinations(digits, 0, "");
        return res;
    }

    public void letterCombinations(String digits, int index, String str) {
        if (index == digits.length()) {
            res.add(str);
            return;
        }
        int num = Integer.valueOf(digits.charAt(index) - '0');//char转int要减0
        if (num <= 1 || num >= 10) {
            return;
        }
        String letterStr = map.get(num);
        for (char c : letterStr.toCharArray()) {
            letterCombinations(digits, index + 1, str + c);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
