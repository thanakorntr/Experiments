package LeetCode;

/**
 * Created by Thanakorn on 8/22/15.
 *
 *  Reverse a linked list from position m to n. Do it in-place and in one-pass.

 For example:
 Given 1->2->3->4->5->NULL, m = 2 and n = 4,

 return 1->4->3->2->5->NULL.

 Note:
 Given m, n satisfy the following condition:
 1 ≤ m ≤ n ≤ length of list.
 */

public class ReverseLinkedListII {

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode reversed = reverseBetween(listNode1, 2, 4);
        while (reversed != null) {
            System.out.println(reversed.val);
            reversed = reversed.next;
        }

    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode curHead = dummyHead;
        int count = 0;
        int numRev = n - m;
        boolean reversed = false;
        while (curHead != null) {
            if (count == m - 1) {  // now curHead points to the node before the m-th node
                ListNode revTail = curHead.next;
                ListNode revNext = revTail.next;
                int i = 0;
                while (i != numRev) {
                    ListNode revNextTmp = revNext.next;
                    revNext.next = revTail;
                    revTail = revNext;
                    i++;
                    if (i == numRev) {
                        curHead.next.next = revNextTmp;
                        curHead.next = revTail;
                        break;
                    }
                    revNext = revNextTmp;
                }
                reversed = true;
            }
            if (reversed) {
                break;
            }
            curHead = curHead.next;
            count++;
        }

        return dummyHead.next;
    }
}
