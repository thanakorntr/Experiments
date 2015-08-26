package LeetCode;

/**
 * Created by Thanakorn on 8/23/15.
 *
 *  Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesInKGroups {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        l4.next = l5;

        ListNode revGroup2 = reverseKGroup(l1, 2);
        ListNode.printListnode(revGroup2);

        ListNode revGroup3 = reverseKGroup(l1, 3);
        ListNode.printListnode(revGroup3);

    }

    public static ListNode reverseKGroup(ListNode head, int k) {

        return null;
    }
}
