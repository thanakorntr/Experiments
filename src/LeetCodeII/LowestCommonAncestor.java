package LeetCodeII;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thanakorn on 3/29/16.
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(-1);
        TreeNode n2 = new TreeNode(0);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(-2);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n4.left = n6;

        System.out.println(new LowestCommonAncestor().lowestCommonAncestor(n1, n6, n5).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        Map<TreeNode, Boolean> pContainMap = new HashMap<>();
        getContainMap(root, p, pContainMap);
        Map<TreeNode, Boolean> qContainMap = new HashMap<>();
        getContainMap(root, q, qContainMap);

        List<TreeNode> pPath = new ArrayList<>();
        getPath(pPath, root, p, pContainMap);
        List<TreeNode> qPath = new ArrayList<>();
        getPath(qPath, root, q, qContainMap);

        for (int pIndex = 0, qIndex = 0; pIndex < pPath.size() && qIndex < qPath.size();
                pIndex++, qIndex++) {
            if (pPath.get(pIndex) != qPath.get(qIndex)) {
                return pPath.get(--pIndex);
            }
        }

        return pPath.size() <= qPath.size() ? pPath.get(pPath.size() - 1) : qPath.get(qPath.size() - 1);
    }

    private void getPath(List<TreeNode> path, TreeNode root, TreeNode destination, Map<TreeNode, Boolean> containMap) {

        path.add(root);

        if (root == destination) {
            return;
        }

        if (root.left != null) {
            if (containMap.get(root.left)) {
                getPath(path, root.left, destination, containMap);
                return;
            }
        }

        if (root.right != null) {
            if (containMap.get(root.right)) {
                getPath(path, root.right, destination, containMap);
            }
        }

    }

    private void getContainMap(TreeNode root, TreeNode t, Map<TreeNode, Boolean> containMap) {
        if (root == t) {
            containMap.put(root, true);
            return;
        }

        if (root.left != null) {
            getContainMap(root.left, t, containMap);
            if (containMap.get(root.left)) {
                containMap.put(root, true);
            }
        }

        if (root.right != null) {
            getContainMap(root.right, t, containMap);
            if (containMap.get(root.right)) {
                containMap.put(root, true);
            }
        }

        if (!containMap.containsKey(root)) {
            containMap.put(root, false);
        }
    }

}
