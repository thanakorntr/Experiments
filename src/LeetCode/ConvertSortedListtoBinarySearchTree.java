package LeetCode; /**
 * Created by Thanakorn on 8/1/15.
 */

/**
 * Definition for singly-linked list.
 * public class LeetCode.ListNode {
 *     int val;
 *     LeetCode.ListNode next;
 *     LeetCode.ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class LeetCode.TreeNode {
 *     int val;
 *     LeetCode.TreeNode left;
 *     LeetCode.TreeNode right;
 *     LeetCode.TreeNode(int x) { val = x; }
 * }
 */

public class ConvertSortedListtoBinarySearchTree {

    public static ListNode h;

    public static void main(String[] args) {

    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        int count = 0;
        ListNode tmp = head;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }

        h = head;
        return sortedListToBSTHelper(0, count-1);
    }

    public TreeNode sortedListToBSTHelper(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (end + start) / 2;
        TreeNode left = sortedListToBSTHelper(start, mid-1);
        TreeNode root = new TreeNode(h.val);
        root.left = left;
        h = h.next;
        root.right = sortedListToBSTHelper(mid+1, end);

        return root;
    }
}
