import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Thanakorn on 7/25/15.
 */
public class MergeKSortedLists {

    public static void main(String[] args) {

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });

        for (ListNode listNode : lists) {
            if (listNode != null) {
                pq.add(listNode);
            }
        }

        ListNode head = new ListNode(0);
        ListNode headTmp = head;

        while (!pq.isEmpty()) {
            ListNode next = pq.poll();
            if (next.next != null) {
                pq.add(next.next);
            }
            head.next = next;
            head = head.next;
        }

        return headTmp.next;
    }


}
