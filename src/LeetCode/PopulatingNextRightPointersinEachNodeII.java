package LeetCode;

/**
 * Created by Thanakorn on 10/5/15.
 * Follow up for problem "Populating Next Right Pointers in Each Node".

 What if the given tree could be any binary tree? Would your previous solution still work?

 Note:

 You may only use constant extra space.

 For example,
 Given the following binary tree,

 1
 /  \
 2    3
 / \    \
 4   5    7

 After calling your function, the tree should look like:

    1 -> NULL
   /  \
  2 -> 3 -> NULL
  / \    \
 4-> 5 -> 7 -> NULL

 */
public class PopulatingNextRightPointersinEachNodeII {

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    public static void main(String[] args) {

    }

    private void connect(TreeLinkNode root) {

        while (root != null) {
            TreeLinkNode tmpStart = new TreeLinkNode(0);
            TreeLinkNode tmp = tmpStart;
            while (root != null) {
                if (root.left != null) {
                    tmp.next = root.left;
                    tmp = tmp.next;
                }
                if (root.right != null) {
                    tmp.next = root.right;;
                    tmp = tmp.next;
                }
                root = root.next;
            }
            tmp.next = null;
            root = tmpStart.next;
        }

    }


}
