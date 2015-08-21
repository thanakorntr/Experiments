package LeetCode;

/**
 * Created by Thanakorn on 8/20/15.
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *
 * For example,
    Given  1->4->3->2->5->2 and x = 3,
    return 1->2->2->4->3->5.
 */
public class PartitionList {

    public static void main(String[] args) {

    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode lesser = new ListNode(0);
        ListNode lesserHead = lesser;
        ListNode greater = new ListNode(0);
        ListNode greaterHead = greater;

        while (head != null) {
            if (head.val < x) {
                lesser.next = head;
                lesser = lesser.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }
        greater.next = null;
        lesser.next = greaterHead.next;
        return lesserHead.next;
    }
}
