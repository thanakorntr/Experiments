package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 8/15/15.
 */
public class BinaryTreePaths {

    public static void main(String[] args) {

    }

    public List<String> binaryTreePaths(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        List<String> paths = new ArrayList<>();
        binaryTreePathsHelper(root, sb, paths);
        return paths;
    }

    public void binaryTreePathsHelper(TreeNode root, StringBuilder sb, List<String> paths) {
        if (root == null) return;

        sb.append(root.val);

        if (root.left == null && root.right == null) {
            paths.add(sb.toString());
            return;
        }

        int prevLen2 = sb.length();
        if (root.left != null) {
            sb.append("->");
            binaryTreePathsHelper(root.left, sb, paths);
            sb.delete(prevLen2, sb.length());
        }
        if (root.right != null) {
            sb.append("->");
            binaryTreePathsHelper(root.right, sb, paths);
            sb.delete(prevLen2, sb.length());
        }

    }
}
