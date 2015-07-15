/**
 * Created by Thanakorn on 7/14/15.
 */
public class DeleteNode {

    public static void main(String[] args) {

        ListNode n1 = new ListNode(0);
        ListNode n2 = new ListNode(1);
        n1.next = n2;

        deleteNode(n1);

        printListNode(n1);
    }

    public static void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }

        ListNode next = node.next;
        while (next != null) {
            node.val = next.val;
            if (next.next == null) {
                node.next = null;
                break;
            }
            node = next;
            next = next.next;
        }

    }

    public static void printListNode(ListNode node ) {

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

    }
}
