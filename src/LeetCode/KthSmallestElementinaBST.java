package LeetCode;

/**
 * Created by Thanakorn on 9/12/15.
 *
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 How would you optimize the kthSmallest routine?

 Hint:

 Try to utilize the property of a BST.
 What if you could modify the BST node's structure?
 The optimal runtime complexity is O(height of BST).

  Definition for a binary tree node.
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

 */
public class KthSmallestElementinaBST {

    class AugmentedTreeNode {
        int val;
        int leftChildrenCount;
        int totalChildrenCount;
        AugmentedTreeNode left;
        AugmentedTreeNode right;
        AugmentedTreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        KthSmallestElementinaBST kthSmallestElementinaBST = new KthSmallestElementinaBST();
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        n1.left = n2;
        System.out.println(kthSmallestElementinaBST.kthSmallest(n1, 1));
    }

    public int kthSmallest(TreeNode root, int k) {
        AugmentedTreeNode augmentedRoot = buildAugmentedTreeNode(root);
        return kthSmallestHelper(augmentedRoot, k);
    }

    private int kthSmallestHelper(AugmentedTreeNode augmentedTreeNode, int k) {
        if (augmentedTreeNode == null) {
            return -1;
        }

        if (augmentedTreeNode.leftChildrenCount == k-1) {
            return augmentedTreeNode.val;
        } else if (augmentedTreeNode.leftChildrenCount < k-1){
            return kthSmallestHelper(augmentedTreeNode.right, k-1-augmentedTreeNode.leftChildrenCount);
        } else {
            return kthSmallestHelper(augmentedTreeNode.left, k);
        }
    }

    private AugmentedTreeNode buildAugmentedTreeNode(TreeNode root) {
        if (root == null) {
            return null;
        }

        AugmentedTreeNode augmentedTreeNode = new AugmentedTreeNode(root.val);
        augmentedTreeNode.left = buildAugmentedTreeNode(root.left);
        augmentedTreeNode.right = buildAugmentedTreeNode(root.right);
        if (augmentedTreeNode.left == null) {
            augmentedTreeNode.leftChildrenCount = 0;
        } else {
            augmentedTreeNode.leftChildrenCount = 1 + augmentedTreeNode.left.totalChildrenCount;
        }

        if (augmentedTreeNode.right == null) {
            augmentedTreeNode.totalChildrenCount = augmentedTreeNode.leftChildrenCount;
        } else {
            augmentedTreeNode.totalChildrenCount = 1 + augmentedTreeNode.leftChildrenCount + augmentedTreeNode.right.totalChildrenCount;
        }

        return augmentedTreeNode;
    }
}
