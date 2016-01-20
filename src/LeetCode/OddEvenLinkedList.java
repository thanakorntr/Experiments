package LeetCode;

/**
 * Created by Thanakorn on 1/19/16.
 */
public class OddEvenLinkedList {


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode fixedHead = head;
        for (int n = 2; n <= 3; n++) {
            head.next = new ListNode(n);
            head = head.next;
        }

        ListNode oddEvenList = oddEvenList(fixedHead);
        ListNode.printListnode(oddEvenList);
    }

    private static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fixedHead = head;
        ListNode odd = new ListNode(0);
        ListNode even = new ListNode(0);
        ListNode fixedEven = even;
        int count = 1;

        while (head != null) {
            if (count % 2 != 0) {
                odd.next = head;
                odd = odd.next;
            } else {
                even.next = head;
                even = even.next;
            }
            count++;
            head = head.next;
        }

        even.next = null;
        odd.next = fixedEven.next;

        return fixedHead;
    }
}
