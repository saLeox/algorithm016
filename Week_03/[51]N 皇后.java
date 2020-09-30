//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例： 
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// Related Topics 回溯算法 
// 👍 616 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    List<List<String>> res = new ArrayList<List<String>>();

    public List<List<String>> solveNQueens(int n) {
        Set<Integer> colSet = new HashSet<Integer>();
        Set<Integer> diagonalSet1 = new HashSet<Integer>();
        Set<Integer> diagonalSet2 = new HashSet<Integer>();
        int[] result = new int[n];
        // 建议变量从0开始
        backTrack(colSet, diagonalSet1, diagonalSet2, n, 0, result);
        return res;
    }

    private void backTrack(Set<Integer> colSet, Set<Integer> diagonalSet1, Set<Integer> diagonalSet2, int n, int row, int[] result) {
        // terminator
        if (row == n) {
            res.add(printQueen(n, result));
            return; // cant forget
        }
        // process logic in current level
        for (int col = 0; col < n; col++) {
            // 进来之后回退
            if (colSet.contains(col)) {
                continue;
            }
            int diagonals1 = col + row;
            if (diagonalSet1.contains(diagonals1)) {
                continue;
            }
            int diagonals2 = col - row;
            if (diagonalSet2.contains(diagonals2)) {
                continue;
            }
            result[row] = col;
            colSet.add(col);
            diagonalSet1.add(diagonals1);
            diagonalSet2.add(diagonals2);
            // drill down
            backTrack(colSet, diagonalSet1, diagonalSet2, n, row + 1, result);
            // reverse status
            // 不要写错变量
            result[row] = -1;
            colSet.remove(col);
            diagonalSet1.remove(diagonals1);
            diagonalSet2.remove(diagonals2);// 写错了一次
        }
    }

    private List<String> printQueen(int n, int[] result) {
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            StringBuffer strBuf = new StringBuffer();
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[result[i]] = 'Q';
            res.add(new String(row));
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
