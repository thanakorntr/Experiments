import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Thanakorn on 6/4/15.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
//        TreeNode n1 = new TreeNode(1);
//        TreeNode n2 = new TreeNode(2);
//        TreeNode n3 = new TreeNode(3);
//        TreeNode n4 = new TreeNode(4);
//        TreeNode n5 = new TreeNode(5);
//
//        n1.left = n2;
//        n1.right = n3;
//        n3.left = n4;
//        n4.right = n5;


//        TreeNode n3 = new TreeNode(3);
//        TreeNode n9 = new TreeNode(9);
//        TreeNode n20 = new TreeNode(20);
//        TreeNode n15 = new TreeNode(15);
//        TreeNode n7 = new TreeNode(7);
//
//        n3.left = n9;
//        n3.right = n20;
//        n20.left = n15;
//        n20.right = n7;


        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n1.left = n2;
        n2.left = n3;
        n3.left = n4;
        n4.left = n5;

        printSerialized(getSerialized(n1));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<String> serialized = getSerialized(root);

        /*
        Input:    [1,2,null,3,null,4,null,5]
        Expected: [[1],[2],[3],[4],[5]]
        */

        List<List<Integer>> ans = new ArrayList<>();

        int serializedIndex = 0;
        int nextMaxCount = 1;
        int currentCount = 0;
        int currentValidCount = 0;

        while (serializedIndex < serialized.size()) {
            List<Integer> subAns = new ArrayList<>();
            while (currentCount < nextMaxCount) {
                if (!serialized.get(serializedIndex).equalsIgnoreCase("#")) {
                    subAns.add(Integer.parseInt(serialized.get(serializedIndex)));
                    currentValidCount++;
                }
                serializedIndex++;
                currentCount++;
            }

            nextMaxCount = 2 * currentValidCount;
            currentCount = 0;
            currentValidCount = 0;

            if (!subAns.isEmpty()) {
                ans.add(subAns);
            }
        }

        return ans;
    }


    public static List<String> getSerialized(TreeNode root) {
        // performing BFS to get serialized
        List<String> serialized = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val != Integer.MIN_VALUE) {
                serialized.add(Integer.toString(node.val));
            } else {
                serialized.add("#");
                continue;
            }

            if (node.left != null) {
                queue.add(node.left);
            } else {
                queue.add(new TreeNode(Integer.MIN_VALUE));
            }
            if (node.right != null) {
                queue.add(node.right);
            } else {
                queue.add(new TreeNode(Integer.MIN_VALUE));
            }
        }

        return serialized;
    }

    public static void printSerialized(List<String> serialized) {
        for (String str : serialized) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
