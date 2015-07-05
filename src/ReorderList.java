/**
 * Created by Thanakorn on 6/12/15.
 */
public class ReorderList {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        reorderList(n1);

        ListNode.print(n1);
        ListNode.print(n1);
    }


    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }

        runner = walker.next;
        walker.next = null;

        ListNode leftP = head.next;
        ListNode rightP = reverse(runner);
        ListNode curP = head;

        while (leftP != null && rightP != null) {
            curP.next = rightP;
            curP = curP.next;
            rightP = rightP.next;

            curP.next = leftP;
            curP = curP.next;
            leftP = leftP.next;
        }

        if (rightP != null) {
            curP.next = rightP;
        }
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.next.next == null) {
            ListNode newHead = head.next;
            newHead.next = head;
            head.next = null;
            return newHead;
        }

        ListNode nextReverseHead = reverse(head.next);
        ListNode nextReverseTail = nextReverseHead;
        while (nextReverseTail.next != null) {
            nextReverseTail = nextReverseTail.next;
        }

        nextReverseTail.next = head;
        head.next = null;

        return nextReverseHead;
    }
}
