package LeetCodeII;

import LeetCode.ListNode;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.

 For example,

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes
 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.
 *
 * Created by Thanakorn on 2/17/16.
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return head;
        }

        int count = 0;
        ListNode walker = head;
        while (walker != null) {
            walker = walker.next;
            count++;
        }

        if (count == n) {
            return head.next;
        }

        int stopNode = count - n;
        count = 1;
        walker = head;

        while (count != stopNode) {
            walker = walker.next;
            count++;
        }

        walker.next = walker.next.next;

        return head;
    }

}
