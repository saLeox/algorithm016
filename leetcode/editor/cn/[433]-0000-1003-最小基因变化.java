//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意: 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 所有的目标基因序列必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 示例 1: 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2: 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3: 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
// 👍 51 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 次数count在此约等于最优层级, 因此要BFS逐层遍历，并记录层级
    public int minMutation(String start, String end, String[] bank) {

        // 准备变量
        int count = 0; // 层级
        HashSet<String> bank_lib = new HashSet<>();
        for (String library : bank) {
            bank_lib.add(library);
        }
        char[] banks = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        queue.offer(start);
        visited.add(start);

        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 逐层遍历
            for (int k = 0; k < size; k++) {
                String tmp = queue.poll();
                // terminator
                if (tmp.equals(end)) {
                    return count;
                }
                // process
                char[] arr = tmp.toCharArray();
                // 逐个字母突变
                for (int i = 0; i < arr.length; i++) {
                    char old = arr[i];
                    for (char b : banks) {
                        if (b == old) {
                            continue; // 避免替换字符重复
                        }
                        arr[i] = b;
                        String newStr = new String(arr);
                        if (!visited.contains(newStr) && bank_lib.contains(newStr)) {
                            visited.add(newStr); //针对的是可能遍历的节点
                            queue.offer(newStr);
                        }
                    }
                    arr[i] = old; //要还原
                }
            }
            count++;
        }
        return -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
