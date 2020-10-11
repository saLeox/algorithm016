//让我们一起来玩扫雷游戏！ 
//
// 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）
//地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。 
//
// 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板： 
//
// 
// 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。 
// 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。 
// 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。 
// 如果在此次点击中，若无更多方块可被揭露，则返回面板。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 
//
//[['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'M', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 示例 2： 
//
// 输入: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'X', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 
//
// 注意： 
//
// 
// 输入矩阵的宽和高的范围为 [1,50]。 
// 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。 
// 输入面板不会是游戏结束的状态（即有地雷已被挖出）。 
// 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 182 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 上 下 左 右 右上 左上 右下 左下
    int[] x_arr = {0, 0, -1, 1, 1, -1, 1, -1};
    int[] y_arr = {1, -1, 0, 0, 1, 1, -1, -1};

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        // [][]是引用传递
        // bfs(board, click);
        dfs(board, click);
        return board;
    }

    // 加visited避免重复访问
    private void bfs(char[][] board, int[] click) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(click);
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[click[0]][click[1]] = true;
        while (!queue.isEmpty()) {
            click = queue.poll();
            // process logic in current level
            int bombNum = 0;
            for (int i = 0; i < 8; i++) {
                int[] xy = {click[0] + x_arr[i], click[1] + y_arr[i]};
                if (xy[0] >= 0 && xy[1] >= 0 && xy[0] < board.length && xy[1] < board[xy[0]].length) {
                    if (board[xy[0]][xy[1]] == 'M') {
                        bombNum++;
                    }
                }
            }
            if (bombNum != 0) {
                board[click[0]][click[1]] = (char) (bombNum + '0');
            } else {
                board[click[0]][click[1]] = 'B';
                for (int i = 0; i < 8; i++) {
                    int[] xy = {click[0] + x_arr[i], click[1] + y_arr[i]};
                    if (xy[0] >= 0 && xy[1] >= 0 && xy[0] < board.length && xy[1] < board[xy[0]].length) {
                        if (board[xy[0]][xy[1]] == 'E' && !visited[xy[0]][xy[1]]) {
                            queue.offer(xy);
                            visited[xy[0]][xy[1]] = true;
                        }
                    }
                }
            }
        }
    }

    private void dfs(char[][] board, int[] click) {
        // terminator
        // 越界
        if (!(click[0] >= 0 && click[1] >= 0 && click[0] < board.length && click[1] < board[click[0]].length)) {
            return;
        }
        // 已点击
        if (board[click[0]][click[1]] != 'E') {
            return;
        }
        // process logic in current level
        int bombNum = 0;
        for (int i = 0; i < 8; i++) {
            int[] xy = {click[0] + x_arr[i], click[1] + y_arr[i]};
            if (xy[0] >= 0 && xy[1] >= 0 && xy[0] < board.length && xy[1] < board[xy[0]].length) {
                if (board[xy[0]][xy[1]] == 'M') {
                    bombNum++;
                }
            }
        }
        // drill out
        if (bombNum != 0) {
            board[click[0]][click[1]] = (char) (bombNum + '0');
        } else {
            board[click[0]][click[1]] = 'B';
            for (int i = 0; i < 8; i++) {
                int[] xy = {click[0] + x_arr[i], click[1] + y_arr[i]};
                dfs(board, xy);
            }
        }
        return;
        // reverse status
    }


}
//leetcode submit region end(Prohibit modification and deletion)
