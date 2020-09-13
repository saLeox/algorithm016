//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1217 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // 迭代解法
    // 双指针: pre用以指向原父节点, next用以记忆下一节点
    // 为指针移动 创建cur节点, 用next覆盖
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(null != cur){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 最后cur为空, pre非空, 所以pre作为头结点返回
        return pre;
    }
    // 递归解法
    // 递归转置剩下节点, 再调整当前节点和剩余节点的位置
    public ListNode reverseList1(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        } else {
            // 此时已经经过反转
            // 假设1-2-3, 当前为1, 剩余部分为2-3
            // 反转后得到的链表是 3-2
            // 当前节点要重新查到 原子节点2的后面, 然后next指针置空
            ListNode node = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return node;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
