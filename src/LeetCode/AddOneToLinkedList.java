package LeetCode;

/**
 * http://www.geeksforgeeks.org/add-1-number-represented-linked-list/
 *
 * Created by Thanakorn on 4/17/16.
 */
public class AddOneToLinkedList {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(9);
        ListNode n3 = new ListNode(9);
        ListNode n4 = new ListNode(9);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        ListNode.printListnode(n1);
        addOne(n1);
        ListNode.printListnode(n1);

        ListNode n5 = new ListNode(1);
        ListNode n6 = new ListNode(2);
        n5.next = n6;
        ListNode.printListnode(n5);
        addOne(n5);
        ListNode.printListnode(n5);
    }

    private static void addOne(ListNode head) {
        if (head == null) {
            return;
        }

        head = reverse(head);
        ListNode fixedReverseHead = head;

        int carry = 1;
        while (head != null) {
            int newVal = head.val + carry;
            carry = newVal / 10;
            newVal = newVal % 10;
            head.val = newVal;
            if (head.next == null) {
                break;
            }
            head = head.next;
        }
        if (carry == 1) {
            head.next = new ListNode(1);
            head = head.next;
        }

        head = reverse(fixedReverseHead);
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            if (next == null) {
                break;
            }
            prev = head;
            head = next;
        }

        return head;
    }

}
