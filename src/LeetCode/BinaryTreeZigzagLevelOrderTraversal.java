package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Thanakorn on 7/19/15.
 */
public class BinaryTreeZigzagLevelOrderTraversal {

/*           3
            / \
           9  20
             /  \
            15  7
*/


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        n1.left = n2;
        n1.right = n3;
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        n3.left = n4;
        n3.right = n5;

        List<List<Integer>> zigzag = zigzagLevelOrder(n1);

        zigzag.forEach(x -> System.out.println(x.toString()));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzag = new ArrayList<>();
        if (root == null) {
            return zigzag;
        }

        List<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);
        int level = 1;

        while (!curLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            List<Integer> tmp = new ArrayList<>();
            if (level % 2 != 0) {
                for (int i = 0; i < curLevel.size(); i++) {
                    TreeNode curNode = curLevel.get(i);
                    tmp.add(curNode.val);
                    if (curNode.left != null) {
                        nextLevel.add(curNode.left);
                    }
                    if (curNode.right != null) {
                        nextLevel.add(curNode.right);
                    }
                }
                zigzag.add(tmp);
                curLevel = nextLevel;
            } else {
                Stack<TreeNode> tmpStack = new Stack<>();
                for (int i = curLevel.size()-1; i >= 0; i--) {
                    TreeNode curNode = curLevel.get(i);
                    tmp.add(curNode.val);
                    if (curNode.right != null) {
                        tmpStack.add(curNode.right);
                    }
                    if (curNode.left != null) {
                        tmpStack.add(curNode.left);
                    }
                }
                while (!tmpStack.isEmpty()) {
                    nextLevel.add(tmpStack.pop());
                }
                zigzag.add(tmp);
                curLevel = nextLevel;
            }
            level++;
        }

        return zigzag;
    }
}
