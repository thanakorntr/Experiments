package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 7/12/15.
 */

/*Given the below binary tree and sum = 22,
         5
        / \
       4   8
      /   / \
     11  13  4
    /  \    / \
   7   2   5   1

        return
        [
        [5,4,11,2],
        [5,8,4,5]
        ]*/

public class PathSumII {

    public static void main(String[] args) {

        int sum = 22;

        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(8);
        TreeNode n4 = new TreeNode(11);
        TreeNode n5 = new TreeNode(13);
        TreeNode n6 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(2);
        TreeNode n9 = new TreeNode(5);
        TreeNode n10 = new TreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n4.left = n7;
        n4.right = n8;
        n3.left = n5;
        n3.right = n6;
        n6.left = n9;
        n6.right = n10;

        List<List<Integer>> ans = pathSum(n1, sum);

        ans.forEach(x -> System.out.println(x.toString()));
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        List<Integer> curSum = new ArrayList<>();

        pathSumHelper(root, sum, curSum, ans);

        return ans;
    }

    public static void pathSumHelper(TreeNode root, int sum, List<Integer> curSum, List<List<Integer>> ans) {

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                curSum.add(root.val);
                List<Integer> tmp = new ArrayList<>(curSum);
                ans.add(tmp);
                curSum.remove(curSum.size()-1);
            }
            return;
        }

        curSum.add(root.val);

        if (root.left != null) {
            pathSumHelper(root.left, sum - root.val, curSum, ans);
        }
        if (root.right != null) {
            pathSumHelper(root.right, sum - root.val, curSum, ans);
        }

        curSum.remove(curSum.size()-1);

    }
}
