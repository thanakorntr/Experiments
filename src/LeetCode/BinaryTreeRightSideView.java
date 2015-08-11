package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 7/20/15.
 */
public class BinaryTreeRightSideView {

    public static void main(String[] args) {

    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> rsv = new ArrayList<>();
        if (root == null) {
            return rsv;
        }

        List<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (int i = 0; i < curLevel.size(); i++) {
                TreeNode curNode = curLevel.get(i);
                if (i == curLevel.size() - 1) {
                    rsv.add(curNode.val);
                }
                if (curNode.left != null) {
                    nextLevel.add(curNode.left);
                }
                if (curNode.right != null) {
                    nextLevel.add(curNode.right);
                }
            }
            curLevel = nextLevel;
        }

        return rsv;
    }

}
