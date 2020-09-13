class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        ListNode prehead = new ListNode(-1); //头指针之前的初始化指针
        ListNode pre = prehead; //前序指针

        while (null != l1 && null != l2) {
            ListNode tmp = null; // 下一指针的值
            if (l1.val <= l2.val) {
                tmp = l1;
                l1 = l1.next;
            } else {
                tmp = l2;
                l2 = l2.next;
            }
            tmp.next = null; // 下一节点断开连接
            pre.next = tmp;
            pre = pre.next; // 赋值后继续向后移动
        }
        while (null != l1) {
            pre.next = l1;
            l1 = l1.next;
            pre.next.next = null;
            pre = pre.next;
        }
        while (null != l2) {
            pre.next = l2;
            l2 = l2.next;
            pre.next.next = null;
            pre = pre.next;
        }
        return prehead.next; // 返回头指针的next
    }
}
//leetcode submit region end(Prohibit modification and deletion)
