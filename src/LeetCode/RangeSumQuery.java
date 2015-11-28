package LeetCode;

/**
 * Created by Thanakorn on 11/27/15.
 */
public class RangeSumQuery {

    class SegmentTreeNode {

        public int startIndex;
        public int endIndex;
        public int sum;

        public SegmentTreeNode leftNode;
        public SegmentTreeNode rightNode;

        public SegmentTreeNode(int startIndex, int endIndex) {
            this(startIndex, endIndex, 0);
        }

        public SegmentTreeNode(int startIndex, int endIndex, int sum) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.sum = sum;
        }
    }

    class NumArray {

        private SegmentTreeNode root;

        public NumArray(int[] nums) {
            if (nums != null && nums.length != 0) {
                root = buildSegmentTree(nums, 0, nums.length - 1);
            }
        }

        private SegmentTreeNode buildSegmentTree(int[] nums, int startIndex, int endIndex) {
            if (startIndex > endIndex) {
                return null;
            } else {
                if (startIndex == endIndex) {
                    return new SegmentTreeNode(startIndex, endIndex, nums[startIndex]);
                } else {
                    SegmentTreeNode segmentTreeNode = new SegmentTreeNode(startIndex, endIndex);
                    int midIndex = startIndex + (endIndex - startIndex) / 2;
                    segmentTreeNode.leftNode = buildSegmentTree(nums, startIndex, midIndex);
                    segmentTreeNode.rightNode = buildSegmentTree(nums, midIndex + 1, endIndex);
                    segmentTreeNode.sum = segmentTreeNode.leftNode.sum + segmentTreeNode.rightNode.sum;
                    return segmentTreeNode;
                }
            }
        }

        void update(int i, int val) {
            if (root != null) {
                updateHelper(root, i, val);
            }
        }

        void updateHelper(SegmentTreeNode node, int i, int val) {
            if (node.startIndex == node.endIndex) {
                node.sum = val;
            } else {
                int midIndex = node.startIndex + (node.endIndex - node.startIndex) / 2;
                if (i <= midIndex) {
                    updateHelper(node.leftNode, i, val);
                } else {
                    updateHelper(node.rightNode, i, val);
                }
                node.sum = node.leftNode.sum + node.rightNode.sum;
            }
        }

        public int sumRange(int i, int j) {
            return sumRangeHelper(root, i, j);
        }

        private int sumRangeHelper(SegmentTreeNode node, int startIndex, int endIndex) {
            if (node.startIndex == startIndex && node.endIndex == endIndex) {
                return node.sum;
            } else {
                int midIndex = node.startIndex + (node.endIndex - node.startIndex) / 2;
                if (endIndex <= midIndex) {
                    return sumRangeHelper(node.leftNode, startIndex, endIndex);
                } else if (startIndex >= midIndex + 1) {
                    return sumRangeHelper(node.rightNode, startIndex, endIndex);
                } else {
                    return sumRangeHelper(node.leftNode, startIndex, midIndex)
                            + sumRangeHelper(node.rightNode, midIndex + 1, endIndex);
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
