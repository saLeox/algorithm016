class Solution {
    List<List<String>> res = new ArrayList<List<String>>();
    HashSet<String> set = new HashSet<String>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return res;
        }
        if (beginWord.equals(endWord)) {
            return res;
        }
        set = new HashSet<String>(wordList);
        set.remove(beginWord);
        bfs_bothWay(beginWord, endWord);
        return res;
    }

    private void bfs_bothWay(String beginWord, String endWord) {
        boolean flag = false; // 和counter一样，必须放在外面，作为全局变量，保证可以跑完最短路径所在层次的所有节点
        Queue<Term> queue1 = new LinkedList<Term>();
        Queue<Term> queue2 = new LinkedList<Term>();
        HashSet<String> visited1 = new HashSet<String>();
        HashSet<String> visited2 = new HashSet<String>();
        Term begin = new Term();
        Term end = new Term();
        begin.str = beginWord;
        end.str = endWord;
        begin.direction = 1;
        end.direction = -1;
        begin.path.add(beginWord);
        end.path.add(endWord);
        queue1.offer(begin);
        queue2.offer(end);
        visited1.add(beginWord);
        visited2.add(endWord);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            if (flag) {
                return;
            }
            if (queue1.size() > queue2.size()) {
                Queue<Term> queue = queue1;
                HashSet<String> visited = visited1;
                queue1 = queue2;
                visited1 = visited2;
                queue2 = queue;
                visited2 = visited;
            }
            int size = queue1.size();
            HashSet<String> visited1_incre = new HashSet<String>(); //避免同一层级之前冲突
            while (size-- > 0) {
                Term term = queue1.poll();
                String str = term.str;
                if (visited2.contains(str)) {
                    flag = true;
                    for (Term t : queue2) {
                        if (t.str.equals(str)) {
                            List<String> oppArr = new ArrayList<String>(t.path);
                            List<String> returnArr = new ArrayList<String>();
                            if (term.direction == 1) {
                                returnArr.addAll(term.path);
                                // 重复的那一项不要
                                for (int i = oppArr.size() - 1; i > 0; i--) {
                                    returnArr.add(oppArr.get(i - 1));
                                }
                            } else {
                                returnArr.addAll(oppArr);
                                for (int i = term.path.size() - 1; i > 0; i--) {
                                    returnArr.add(term.path.get(i - 1));
                                }
                            }
                            res.add(returnArr);
                        }
                    }
                    continue;
                }
                char[] arr = str.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char old = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) {
                            continue;
                        }
                        arr[i] = c;
                        String tmp = new String(arr);
                        if (!visited1.contains(tmp) && set.contains(tmp)) {
                            visited1_incre.add(tmp);
                            List<String> newArr = new ArrayList<String>(term.path);
                            newArr.add(tmp);
                            Term newTerm = new Term();
                            newTerm.str = tmp;
                            newTerm.direction = term.direction;
                            newTerm.path = newArr;
                            queue1.offer(newTerm);
                        }
                        arr[i] = old;
                    }
                }
            }
            visited1.addAll(visited1_incre);
        }
        return;
    }

    class Term {
        String str;
        List<String> path = new ArrayList<String>();
        int direction;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
