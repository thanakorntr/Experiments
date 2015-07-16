import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 7/15/15.
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (left == null) {
            return right;
        } else {
            return left;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }

        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();
        getPath(root, p, pPath);
        getPath(root, q, qPath);

        int pPointer = 0, qPointer = 0;
        while (pPointer < pPath.size() && qPointer < qPath.size()) {
            if (pPath.get(pPointer) != qPath.get(qPointer)) {
                return pPath.get(--pPointer);
            }
            pPointer++;
            qPointer++;
        }

        return pPath.get(--pPointer);
    }

    public void getPath(TreeNode root, TreeNode t, List<TreeNode> path) {
        if (root == null) {
            return;
        }
        if (root == t) {
            path.add(t);
            return;
        }

        if (hasTreeNode(root.left, t)) {
            path.add(root);
            getPath(root.left, t, path);
            return;
        }
        if (hasTreeNode(root.right, t)) {
            path.add(root);
            getPath(root.right, t, path);
        }
    }

    public boolean hasTreeNode(TreeNode root, TreeNode t) {
        if (root == null) {
            return false;
        }
        if (root == t) {
            return true;
        }
        return hasTreeNode(root.left, t) || hasTreeNode(root.right, t);
    }

}
