import java.util.*;

/**
 * Created by Thanakorn on 7/12/15.
 */
public class UniqueBSTII {

    public static void main(String[] args) {
        generateTrees(7);
    }

    public static List<TreeNode> generateTrees(int n) {

        if (n < 1) {
            List<TreeNode> ans = new ArrayList<>();
            ans.add(null);
            return ans;
        }

        Map<Integer, Map<Integer, List<TreeNode>>> memo = new HashMap<>();
        for (int i = 0; i <= n+1; i++) {
            memo.put(i, new HashMap<>());
        }

        return generateTreesHelper(1, n, memo);
    }

    public static List<TreeNode> generateTreesHelper(int start, int end, Map<Integer, Map<Integer, List<TreeNode>>> memo) {

        List<TreeNode> treeNodes = new ArrayList<>();

        if (start == end) {
            treeNodes.add(new TreeNode(start));
            return treeNodes;
        }

        for (int i = start; i <= end; i++) {
            if (i == start) {
                if (memo.get(start+1).containsKey(end)) {
                    for (TreeNode rightSubTree : memo.get(start+1).get(end)) {
                        TreeNode tmp = new TreeNode(start);
                        tmp.right = rightSubTree;
                        treeNodes.add(tmp);
                    }
                } else {
                    List<TreeNode> subTreeNodesTmp = generateTreesHelper(start+1, end, memo);
                    memo.get(start+1).put(end, subTreeNodesTmp);
                    for (TreeNode rightSubTree : subTreeNodesTmp) {
                        TreeNode tmp = new TreeNode(start);
                        tmp.right = rightSubTree;
                        treeNodes.add(tmp);
                    }
                }
            } else if (i == end) {
                if (memo.get(start).containsKey(end-1)) {
                    for (TreeNode leftSubTree : memo.get(start).get(end-1)) {
                        TreeNode tmp = new TreeNode(end);
                        tmp.left = leftSubTree;
                        treeNodes.add(tmp);
                    }
                } else {
                    List<TreeNode> subTreeNodesTmp = generateTreesHelper(start, end-1, memo);
                    memo.get(start).put(end-1, subTreeNodesTmp);
                    for (TreeNode leftSubTree : subTreeNodesTmp) {
                        TreeNode tmp = new TreeNode(end);
                        tmp.left = leftSubTree;
                        treeNodes.add(tmp);
                    }
                }
            } else {
                List<TreeNode> leftSubTrees;
                if (memo.get(start).containsKey(i-1)) {
                    leftSubTrees = memo.get(start).get(i-1);
                } else {
                    leftSubTrees = generateTreesHelper(start, i-1, memo);
                    memo.get(start).put(i-1, leftSubTrees);
                }

                List<TreeNode> rightSubTrees;
                if (memo.get(i+1).containsKey(end)) {
                    rightSubTrees = memo.get(i+1).get(end);
                } else {
                    rightSubTrees = generateTreesHelper(i+1, end, memo);
                    memo.get(i+1).put(end, rightSubTrees);
                }

                for (TreeNode leftSubTree : leftSubTrees) {
                    for (TreeNode rightSubTree : rightSubTrees) {
                        TreeNode tmp = new TreeNode(i);
                        tmp.left = leftSubTree;
                        tmp.right = rightSubTree;
                        treeNodes.add(tmp);
                    }
                }
            }
        }

        memo.get(start).put(end, treeNodes);
        return treeNodes;
    }
}
