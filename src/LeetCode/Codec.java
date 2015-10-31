package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 10/28/15.
 *
 * For example, you may serialize the following tree

        1
       / \
      2   3
         / \
        4   5

 as "[1,2,3,null,null,4,5]"
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        List<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);
        while (!curLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node : curLevel) {
                if (node != null) {
                    stringBuilder.append(node.val).append(" ");
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                } else {
                    stringBuilder.append("_").append(" ");
                }
            }
            curLevel = nextLevel;
        }

        return stringBuilder.toString().trim();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }

        String[] tokens = data.split(" ");

        List<TreeNode> treeNodes = new ArrayList<>();
        for (String token : tokens) {
            if (!token.equals("_")) {
                treeNodes.add(new TreeNode(Integer.parseInt(token)));
            } else {
                treeNodes.add(null);
            }
        }

        int maxNumNodesInCurLevel = 1;
        int startIndexInCurLevel = 0;

        while (startIndexInCurLevel < treeNodes.size()) {

            int numNonNullNodesInCurLevel = 0;

            for (int curIndex = startIndexInCurLevel; curIndex < treeNodes.size() && curIndex < startIndexInCurLevel + maxNumNodesInCurLevel; curIndex++) {
                if (treeNodes.get(curIndex) != null) {
                    numNonNullNodesInCurLevel++;
                }
            }

            if (numNonNullNodesInCurLevel == 0) {
                break;
            }

            int maxNumNodeInNextLevel = numNonNullNodesInCurLevel * 2;

            int startIndexInNextLevel = startIndexInCurLevel + maxNumNodeInNextLevel;

            int tmpStartNextLevelIndex = startIndexInNextLevel;

            for (int curIndex = startIndexInCurLevel; curIndex < treeNodes.size() && curIndex < startIndexInCurLevel + maxNumNodesInCurLevel; curIndex++) {
                if (treeNodes.get(curIndex) != null) {
                    if (tmpStartNextLevelIndex < treeNodes.size()) {
                        treeNodes.get(curIndex).left = treeNodes.get(tmpStartNextLevelIndex++);
                    }
                    if (tmpStartNextLevelIndex < treeNodes.size()) {
                        treeNodes.get(curIndex).right = treeNodes.get(tmpStartNextLevelIndex++);
                    }
                }
            }

            startIndexInCurLevel = startIndexInNextLevel;
            maxNumNodesInCurLevel = maxNumNodeInNextLevel;
        }


        return treeNodes.get(0);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        TreeNode l1 = new TreeNode(2);
        TreeNode l2 = new TreeNode(3);
        root.left = l1;
        root.right = l2;
        TreeNode l3 = new TreeNode(2);
        TreeNode l4 = new TreeNode(4);
        l2.left = l3;
        l2.right = l4;
        l3.left = new TreeNode(3);
        l3.right = new TreeNode(1);

        Codec codec = new Codec();
        String serialized = codec.serialize(root);
        TreeNode deserialized = codec.deserialize(serialized);

        String tmp = "1 3 _ _ 4";
        codec.deserialize(tmp);
    }
}