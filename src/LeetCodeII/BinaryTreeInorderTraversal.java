package LeetCodeII;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Thanakorn on 2/20/16.
 */
public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        n1.left = new TreeNode(2);
        List<Integer> list = inorderTraversal(n1);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        while (root != null) {
            stack.add(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            list.add(cur.val);

            cur = cur.right;
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
        }

        return list;
    }

}
