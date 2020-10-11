学习笔记

搜索-遍历
	每个节点仅访问一次
	顺序不同分为：深度优先DFS 和广度优先BFS 或者启发式搜索(优先级优先)

	深度优先 DFS

		template:

			# set to record the visited node
			# add visited node
			# loop: judge whether have not been visisted, then pass into next dfs

		也可以用栈表示
		每一个节点的递归完成之后，才能继续迭代到下一个节点
		DFS同样适用于图的遍历

	广度优先 BFS

		template1: without level
			# initiate a queue and insert the start node first
			# set start node visited
			# queue loop -> pop, update visited status and process, insert the children nodes into queue
			PS: visited status arr are not compulsory

		template2: with level
			# initiate a queue and insert the start node first
			# set start node visited
			# queue loop -> iterate the existing val using for-iteration
			# In for-iteration, pop, update visited status and process, insert the children nodes into queue
			PS: visited status arr are not compulsory

		template3: both side
			# initiate two queues and insert the start node and end node separately
			# set start node and end node visited
			# queue loop -> set the smaller queue as queue1 and make exchange -> iterate the existing val using for-iteration
			# In for-iteration, pop, update visited status and process, insert the children nodes into queue
			# finish condition: current element contained in queue2



		template1 vs template2:
			区别在于 一次poll一个元素, 还是用for循环把上一层元素全部poll完
			第二种方式，常用于寻找最优解

		template3:
			比1和2的效率更高

		用队列维护，链表或者双端队列
		特点是先入先出


贪心算法：当下做局部最优判断
回溯：能够回退
动态规划：最优判断 + 回退


使用贪心法，需要证明为何能够使用
条件：问题能够分解为子问题，子问题的最优解能递推到最终问题的最优解
coin change例子中，前面的硬币都是后面硬币的倍数，此时才能使用
（柠檬汽水找零的例子符合该情况）



	posh/pop 栈的一进一出
	offer/poll 队列的一进一出

	数组[]也是引用传递, 只有基本数据类型才是值传递

	// '1' -> 1
	char c9 = '1';
	int num9 = c9 - '0';
	 
	// 1 -> '1'
	int num10 = 1;
	char c10 = (char)(num10 + '0');


	Set<String> wordSet = new HashSet<>(wordList);

	for (char c = 'a'; c <= 'z'; c++) {
	}