package LeetCode;

/**
 * Created by Thanakorn on 7/20/15.
 */

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

public class PopulatingNextRightPointersinEachNode {

    public static void main(String[] args) {

    }

    public void connect(TreeLinkNode root) {
        setRightMostToNull(root);
        setBetween(root);
        setBetween2(root);
    }

    public void setRightMostToNull(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        root.next = null;
        setRightMostToNull(root.right);
    }

    public void setBetween(TreeLinkNode root) {
        if (root == null || root.left == null) {
            return;
        }
        root.left.next = root.right;
        setBetween(root.left);
        setBetween(root.right);
    }

    public void setBetween2(TreeLinkNode root) {
        if (root == null || root.left == null || root.left.left == null) {
            return;
        }
        TreeLinkNode left = root.left.right;
        TreeLinkNode right = root.right.left;
        while (left != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }
        setBetween2(root.left);
        setBetween2(root.right);
    }
}
