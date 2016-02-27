package LeetCodeII;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 For example:
 Given the below binary tree and sum = 22,
 5
 / \
 4   8
 /   / \
 11  13  4
 /  \    / \
 7    2  5   1
 return
 [
 [5,4,11,2],
 [5,8,4,5]
 ]
 *
 * Created by Thanakorn on 2/24/16.
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> pathSum = new ArrayList<>();
        if (root == null) {
            return pathSum;
        }
        pathSumHelper(pathSum, new ArrayList<>(), root, sum);
        return pathSum;
    }

    private void pathSumHelper(List<List<Integer>> pathSum,
                               List<Integer> curPathSum,
                               TreeNode root,
                               int sum) {

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                curPathSum.add(root.val);
                pathSum.add(new ArrayList<>(curPathSum));
                curPathSum.remove(curPathSum.size() - 1);
            }
            return;
        }

        curPathSum.add(root.val);

        if (root.left != null) {
            pathSumHelper(pathSum, curPathSum, root.left, sum - root.val);
        }
        if (root.right != null) {
            pathSumHelper(pathSum, curPathSum, root.right, sum - root.val);
        }

        curPathSum.remove(curPathSum.size() - 1);
    }

}
