//机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令： 
//
// 
// -2：向左转 90 度 
// -1：向右转 90 度 
// 1 <= x <= 9：向前移动 x 个单位长度 
// 
//
// 在网格上有一些格子被视为障碍物。 
//
// 第 i 个障碍物位于网格点 (obstacles[i][0], obstacles[i][1]) 
//
// 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。 
//
// 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。 
//
// 
//
// 示例 1： 
//
// 输入: commands = [4,-1,3], obstacles = []
//输出: 25
//解释: 机器人将会到达 (3, 4)
// 
//
// 示例 2： 
//
// 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
//输出: 65
//解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
// 
//
// 
//
// 提示： 
//
// 
// 0 <= commands.length <= 10000 
// 0 <= obstacles.length <= 10000 
// -30000 <= obstacle[i][0] <= 30000 
// -30000 <= obstacle[i][1] <= 30000 
// 答案保证小于 2 ^ 31 
// 
// Related Topics 贪心算法 
// 👍 111 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // 途径的最大欧氏距离
        int maxDistSquare = 0;
        // 方向控制
        int[][] directionArr = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionFlag = 0;
        // 当前坐标
        int[] location = new int[]{0, 0};
        // 存放障碍
        HashSet<String> set = new HashSet<String>(); // HashSet<int[]>()无法调用hashcode计算方法，无法判断重复
        if (obstacles.length > 0) {
            for (int i = 0; i < obstacles.length; i++) {
                set.add(obstacles[i][0] + " " + obstacles[i][1]);
            }
        }
        // 开始行走
        for (int i = 0; i < commands.length; i++) {
            int x = commands[i];
            // 左转90°
            if (x == -2) directionFlag = (directionFlag + 3) % 4;
            // 右转90°
            else if (x == -1) directionFlag = (directionFlag + 1) % 4;
            // 延当前方向行走
            else {
                while (x-- > 0) {
                    int x_tmp = location[0] + directionArr[directionFlag][0];
                    int y_tmp = location[1] + directionArr[directionFlag][1];
                    if (set.contains(x_tmp + " " + y_tmp)) {
                        break; //如果有陷阱则原地不动
                    }
                    location[0] = x_tmp;
                    location[1] = y_tmp;
                }
                maxDistSquare = Math.max(maxDistSquare, location[0] * location[0] + location[1] * location[1]);
            }
        }
        return maxDistSquare;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
