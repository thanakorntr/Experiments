package LeetCode;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.

 For example:
 Given 1->2->3->4->5->NULL and k = 2,
 return 4->5->1->2->3->NULL.
 *
 * Created by Thanakorn on 2/13/16.
 */
public class RotateList {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        ListNode rotatedList = rotateRight(l1, 1);
        ListNode.printListnode(rotatedList);
    }

    private static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int count = 0;
        ListNode walker = head;
        ListNode tail = null;
        while (walker != null) {
            if (walker.next == null) {
                tail = walker;
            }
            walker = walker.next;
            count++;
        }

        if (k % count == 0) {
            return head;
        }

        int numRightPart = count - (k % count);
        int rightPartCount = 1;
        walker = head;
        while (rightPartCount != numRightPart) {
            walker = walker.next;
            rightPartCount++;
        }

        ListNode headRightPart = head;
        ListNode tailRightPart = walker;
        ListNode headLeftPart = walker.next;
        ListNode tailLeftPart = tail;
        tailRightPart.next = null;
        tailLeftPart.next = headRightPart;
        return headLeftPart;
    }

}
