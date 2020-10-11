//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 788 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                    // bfs(grid, i, j);
                }
            }
        }
        return res;
    }

    // boolean[][] visited = new boolean[grid.length][grid[0].length];
    // 没有必要加visited 因为样本数量少
    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{i, j});
        // visited.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            i = xy[0];
            j = xy[1];
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) {
                continue; //结束当前while, 不能return
            }
            if (grid[i][j] == '0') {
                continue;
            }
            /*
            if (visited.contains(xy)) {
                continue;
            }
            */
            grid[i][j] = '0';
            System.out.println("x:" + i + " y:" + j);
            // visited.add(xy);
            queue.offer(new int[]{i - 1, j});
            queue.offer(new int[]{i + 1, j});
            queue.offer(new int[]{i, j - 1});
            queue.offer(new int[]{i, j + 1});
        }
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
