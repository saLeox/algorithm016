学习笔记

1, 递归
	参数至少包括两个 level 和 param

		level表示进展程度的变量，可以不至一个，例如左右
		param为体现递归处理的变量，归去来兮，来回传递，要从空开始，否则会挖坑

构成如下：

		A，终止函数 # recursion terminator
		B，处理当前层的逻辑 # process logic in current level
		C, 下探下一层 # drill down
		D, 清理状态 # reverse the current level status if needed

	最终输出结果：
		如果是数组的话，可以设为成员变量
		在终止时添加并返回

	要点-数学归纳法, 理顺N和N-1/N+1的关系
	B和C位置可以对调

2, 分治/回溯

	最优重复性 - 动态规划
	最近重复性 - 分治/回溯

分治:

	# recursion terminator
	# prepare data == process logic in current level
	# conquer subproblems == drill down
	# process and generate the final result 区别于普通递归的地方
	# revert current level status
	
回溯：

	试错思想, 分步解决问题, 错误则取消上一步或上几步的计算
	越早判定并否定当前结果, 可以节省时间和次数
	模板同普通递归


做题笔记

	1, 搜索二叉树的每个节点有上下边界,并且可以传递给更下层的子节点
	2, 搜索二叉树中序遍历是有序的
	3, 前序遍历与中序遍历的区间长度是一致的

	4, 数值超过16位用long接受参数, long是32位的

	5, char转int要减0 
		int num = Integer.valueOf(digits.charAt(index) - '0');

	6, HashMap初始化加双花括号
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

	7, boolean[] used = new boolean[nums.length]
	可以替代 HashSet<Integer> 可以提高速度，尽管hashtable查询、新增、删除都是O(1)

	8, 需要反复遍历得到的值, 可以预先用hashmap预先存起来
        while (inorder[inRootIndex] != rootVal) {
            inRootIndex++;
        }
        优化为：
	    private Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
	    for (int i = 0; i < inorder.length; i++) {
	        indexMap.put(inorder[i], i);
	    }