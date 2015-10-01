package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 9/28/15.
 * <p>
 * Given an array of integers, find out whether there are two distinct indices i and j in
 * the array such that the difference between nums[i] and nums[j] is at most t and the difference
 * between i and j is at most k.
 */
public class ContainsDuplicateIII {

    private static class BSTNode {
        public int val;
        public BSTNode left;
        public BSTNode right;
        public BSTNode parent;

        public BSTNode(int val) {
            this.val = val;
        }
    }

    private static class BST {
        public BSTNode root;

        public void add(BSTNode bstNode) {
            addHelper(this.root, bstNode);
        }

        private void addHelper(BSTNode root, BSTNode bstNode) {
            if (bstNode == null) {
                return;
            }

            if (root == null) {
                this.root = bstNode;
            } else {
                if (bstNode.val == root.val) {
                    return;
                } else if (bstNode.val < root.val) {
                    if (root.left == null) {
                        root.left = bstNode;
                        bstNode.parent = root;
                    } else {
                        addHelper(root.left, bstNode);
                    }
                } else if (bstNode.val > root.val) {
                    if (root.right == null) {
                        root.right = bstNode;
                        bstNode.parent = root;
                    } else {
                        addHelper(root.right, bstNode);
                    }
                }
            }
        }

        public void delete(int val) {
            deleteHelper(this.root, val);
        }

        private void deleteHelper(BSTNode root, int val) {
            if (root == null) {
                return;
            }

            if (root.val == val) {
                if (root == this.root) {
                    if (root.left == null && root.right == null) {
                        this.root = null;
                        return;
                    }
                    if (root.left == null) {
                        this.root = this.root.right;
                        return;
                    }
                    if (root.right == null) {
                        this.root = this.root.left;
                        return;
                    }
                    // TODO
                    BSTNode minRight = getMinimum(root.right);


                    return;
                }


                if (root.left == null && root.right == null) {
                    if (root.parent.left == root) {
                        root.parent.left = null;
                    } else if (root.parent.right == root) {
                        root.parent.right = null;
                    }
                    return;
                }
                if (root.left == null) {
                    if (root.parent.left == root) {
                        root.parent.left = root.right;
                    } else if (root.parent.right == root) {
                        root.parent.right = root.right;
                    }
                    root.right.parent = root.parent;
                    return;
                }
                if (root.right == null) {
                    if (root.parent.left == root) {
                        root.parent.left = root.left;
                    } else if (root.parent.right == root) {
                        root.parent.right = root.left;
                    }
                    root.left.parent = root.parent;
                    return;
                }

                // TODO
            } else if (root.val < val) {
                deleteHelper(root.right, val);
            } else {
                deleteHelper(root.left, val);
            }
        }

        // find if there is a value in the [low, high] range in the BST
        public boolean findInterval(int low, int high) {
            return findIntervalHelper(this.root, low, high);
        }

        private boolean findIntervalHelper(BSTNode root, int low, int high) {
            if (root == null) {
                return false;
            }

            if (root.val >= low && root.val <= high) {
                return true;
            } else if (root.val > high) {
                return findIntervalHelper(root.left, low, high);
            } else  {
                return findIntervalHelper(root.right, low, high);
            }
        }

        private BSTNode getMinimum(BSTNode root) {
            if (root == null) {
                return null;
            }
            if (root.left == null) {
                return root;
            }
            return getMinimum(root.left);
        }
    }



    public static void main(String[] args) {

        int[] nums = new int[] {1,2,3,4,5};
        int k = 3;
        int t = 2;
        containsNearbyAlmostDuplicate(nums, k, t);

    }

    // window size = k+1
    private static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        Map<Integer, Integer> numCountMap = new HashMap<>();
        BST bst = new BST();
        for (int i = 0; i < k+1; i++) {
            bst.add(new BSTNode(nums[i]));
            if (!numCountMap.containsKey(nums[i])) {
                numCountMap.put(nums[i], 1);
            } else {
                numCountMap.put(nums[i], numCountMap.get(nums[i]) + 1);
            }
        }

        // TODO: check if the first window contains it

        for (int i = k+1; i < nums.length; i++) {
            int prevValToRemove = nums[i - k - 1];
            if (numCountMap.get(prevValToRemove) == 1) {
                numCountMap.remove(prevValToRemove);
                bst.delete(prevValToRemove);
            } else {
                numCountMap.put(prevValToRemove, numCountMap.get(prevValToRemove) - 1);
            }

            int high = t + nums[i];
            int low = nums[i] - t;
            if (bst.findInterval(low, high)) {
                return true;
            }

            if (!numCountMap.containsKey(nums[i])) {
                numCountMap.put(nums[i], 1);
                bst.add(new BSTNode(nums[i]));
            } else {
                numCountMap.put(nums[i], numCountMap.get(nums[i]) + 1);
            }
        }

        return false;
    }
}
