package LeetCode;

/**
 * Created by Thanakorn on 6/1/15.
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }

    public static void printListnode(ListNode head) {
        if (head == null) return;

        StringBuilder stringBuilder = new StringBuilder();

        while (head.next != null) {
            stringBuilder.append(head.val).append("->");
            head = head.next;
        }

        if (head != null) {
            stringBuilder.append(head.val);
        }

        System.out.println(stringBuilder.toString());
    }
}
