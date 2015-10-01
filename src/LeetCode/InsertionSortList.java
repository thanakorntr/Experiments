package LeetCode;

/**
 * Created by Thanakorn on 9/30/15.
 *
 * Sort a linked list using insertion sort.
 */
public class InsertionSortList {

    public static void main(String[] args) {

        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode.printListnode(n1);
        ListNode n = insertionSortList(n1);
        ListNode.printListnode(n);
    }

    private static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode nextSorted = insertionSortList(head.next);
        if (head.val <= nextSorted.val) {
            head.next = nextSorted;
            return head;
        }

        ListNode nextSortedTmp = nextSorted;
        while (nextSortedTmp.next != null && nextSortedTmp.next.val < head.val) {
            nextSortedTmp = nextSortedTmp.next;
        }

        ListNode nextNextTmp = nextSortedTmp.next;
        nextSortedTmp.next = head;
        head.next = nextNextTmp;

        return nextSorted;
    }
}
