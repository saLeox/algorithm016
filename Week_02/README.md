学习笔记
哈希表、映射、集合
	哈希函数-映射得到下标
		查询时间复杂度为O（1）

	hashcode在java中需要重载
		期望分布尽可能分散，不发生 哈希碰撞

	哈希函数：简单的做法
		ASCII码加在一起，mod特定的数

	拉链式解决冲突法(哈希碰撞)--hash对应的index用链表存储数据，但是时间复杂度会上升到O(N)的级别

	hashset底层实现=hashmap + new object占位符

二叉树
	树和图的区别，是否有环

	链表->树->图

	树的遍历：前序(根左右)、中序(左根右, 升序的)、后续(左右根)
		前后中是指根节点的次序
		迭代 -- 手动维护栈实现
			前序 -- 从顶点开始入栈，后继进栈元素及顺序：右节点 -> 左节点
			中序 -- 前往最左侧节点，且依次存入左节点，左节点不存在时，输出栈顶元素，当前节点指向右节点，并存入栈
			后续 -- 前往最左侧节点，且依次存入左节点，左节点不存在时，指向栈顶元素的右节点，并存入栈，不存在输出栈顶元素
		广度优先通过队列实现 Queue<Node> queue = new LinkedList<Node>();
		深度优先通过栈实现 Stack<Node> stack = new Stack<Node>();


	二叉搜索树->有序，否则搜索需要O(N)复杂度
		特征：
			左子树所有节点小于根节点的值
			右子树所有节点小于根节点的值

		常见操作时间复杂度：
			查询、插入 O(logN) << O(N)

		创建 = 不断插入新节点

		删除：
			叶子节点 -- 直接删除即可
			根节点 - 选取右子树中最小的节点，作为新的根节点

堆:
	一种能够快速找到最值的数据结构
	实现:
		二叉堆(实现最简单,效率低), 斐波拉契堆, 严格斐波拉契堆(效率最高),还有及其
	API及其复杂度:
		find-max	O(1)
		delete-max	O(logN)
		insert 		O(logN) or O(1)

	二叉堆
		通过完全二叉树实现
		任意节点值>子节点值
		通过数组实现
			左孩子坐标 2*i+1
			右孩子左边 2*i+2
			父节点坐标 (i-1)/2
		插入操作:
			置于数组尾部,通过与父节点交换,向上浮动(heapifyUp)
			 -- 保证根节点是一堆数中的最大节点
		删除堆顶元素操作:
			堆尾元素替换栈顶元素，长度减一，向下浮动(heapifyDown)，与较大的子节点进行交换
		调用 - java的实现 - 优先队列 priority queue

图
	构成：点 vertex + 边edge
		vertex
			1, 度 - 入度和出度
			2，连通性 - 点与点之间，直接或间接
		edge
			1, 有向、无向
			2, 权重 - length OR cost
	表示方法：
		邻接表 - adjacency matrix 
			N*N的矩阵 N表示点的下标 值为权重，大于0意为有直接的边相连
				无向 - 没有方向，矩阵沿主对角线对称
				无权 - 只有连通与非连通的区别 0/1
		邻接表 - adjacency table
			N个链表 - 存放和当前点直接相连的点
		算法应用：
			DFS/BFS visited集合 SET一定要加,避免重复访问节点，后面章节有相应内容
			拓扑排序与DFS/BFS有关
			最小生成树 https://www.bilibili.com/video/av84820276?from=search&seid=17476598104352152051
			最短路径 https://www.bilibili.com/video/av25829980?from=search&seid=13391343514095937158



常用API笔记：
map.containsKey()
map.get() -- 获得主键对应的值, 数组用[]取，注意区分
map.values() -- 返回collection对象 作为参数传进array的构造函数，可不需要遍历map(更加简洁)
map.get(key).add(str); value为集合时可以直接基于引用修改内容，不用put操作
for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
    xx = entry.getKey();
    xx = entry.getValue();
}

Arrays.sort() -- 数组快速排序，时间复杂度为 nlogn
Arrays.equal() -- 判断两个数组是否相同
List通过ArrayList初始化之后才能赋给List

Stack<Node> stack = new Stack<Node>();
stack.push();
stack.pop();
stack.peek();
stack.empty();

Queue<Node> queue = new LinkedList<Node>(); //LinkedList是Queue的一种实现
queue.add();
queue.addAll(); //加入集合
queue.poll(); //返回首个元素

PriorityQueue<Integer> heap = new PriorityQueue<>((v1, v2) -> v1 - v2); //小根堆
PriorityQueue<Integer> heap = new PriorityQueue<>((v1, v2) -> v2 - v1); //大根堆
heap.poll();
heap.add();

char[]和string相互转换
	char[] arr = str.toCharArray();
	return String.valueOf(arr);	

int[] counter = new int[26];
	初始化之后各位置初始值为0
	counter[s.charAt(i) - 'a'] 通过相对值定位