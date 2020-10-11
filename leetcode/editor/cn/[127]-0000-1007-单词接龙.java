//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
//
//
//
// 每次转换只能改变一个字母。
// 转换过程中的中间单词必须是字典中的单词。
//
//
// 说明:
//
//
// 如果不存在这样的转换序列，返回 0。
// 所有单词具有相同的长度。
// 所有单词只由小写字母组成。
// 字典中不存在重复的单词。
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
//
//
// 示例 1:
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
//
//
// 示例 2:
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。
// Related Topics 广度优先搜索
// 👍 456 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<Character> charArr = new ArrayList<Character>();
    Set<String> wordSet = new HashSet<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 0;
        }
        // wordList存到hash中，可以加速
        wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);
        return bfs_bothway(beginWord, endWord);
        // return bfs(beginWord, endWord);
    }

    private int bfs_bothway(String beginWord, String endWord) {
        int counter = 1;
        // 正向
        Queue<String> queue1 = new LinkedList<String>();
        HashSet<String> visited1 = new HashSet<String>();
        queue1.offer(beginWord);
        visited1.add(beginWord);
        // 反向
        Queue<String> queue2 = new LinkedList<String>();
        HashSet<String> visited2 = new HashSet<String>();
        queue2.offer(endWord);
        visited2.add(endWord);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            // 先从小的那边开始, 固定将queue1作为小的，queue2为大的
            // 如果queue1.size较大,则对调位置
            if (queue1.size() > queue2.size()) {
                // 否则对调位置
                Queue<String> queue_tmp = queue1;
                HashSet<String> visited_tmp = visited1;
                queue1 = queue2;
                visited1 = visited2;
                queue2 = queue_tmp;
                visited2 = visited_tmp;
            }
            int size = queue1.size();
            while (size-- > 0) {
                String str = queue1.poll();
                // 终止条件改为 另外一个队列也访问过该词
                if (visited2.contains(str)) {
                    return counter;
                }
                char[] cArr = str.toCharArray();
                for (int i = 0; i < cArr.length; i++) {
                    char old = cArr[i];
                    // 直接遍历26个字母
                    for (char c = 'a'; c <= 'z'; c++) {
                        //for (Character c : charArr) {
                        if (c == old) {
                            continue;
                        }
                        cArr[i] = c;
                        String tmp = new String(cArr);
                        if (wordSet.contains(tmp) && !visited1.contains(tmp)) {
                            queue1.offer(tmp);
                            visited1.add(tmp);
                        }
                        cArr[i] = old;
                    }
                }
            }
            counter++;
        }
        return 0;
    }


    private int bfs(String beginWord, String endWord) {
        int counter = 1;
        Queue<String> queue = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String str = queue.poll();
                if (str.equals(endWord)) {
                    return counter;
                }
                char[] cArr = str.toCharArray();
                for (int i = 0; i < cArr.length; i++) {
                    char old = cArr[i];
                    // 直接遍历26个字母
                    for (char c = 'a'; c <= 'z'; c++) {
                        //for (Character c : charArr) {
                        if (c == old) {
                            continue;
                        }
                        cArr[i] = c;
                        String tmp = new String(cArr);
                        if (wordSet.contains(tmp) && !visited.contains(tmp)) {
                            queue.offer(tmp);
                            visited.add(tmp);
                        }
                        cArr[i] = old;
                    }
                }
            }
            counter++;
        }
        return 0;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
