package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 7/25/15.
 */

class CSNodeII {
    int val;
    List<CSNodeII> prerequisiteOf;
    boolean isTraversed;

    CSNodeII(int val) {
        this.val = val;
        prerequisiteOf = new ArrayList<>();
        isTraversed = false;
    }
}

class CSGraphII {
    List<CSNodeII> csNodes;

    CSGraphII() {
        csNodes = new ArrayList<>();
    }
}

public class CourseScheduleII {

    public static void main(String[] args) {

    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses < 1) {
            return new int[0];
        }

        // initialize all nodes and put them in a graph
        CSGraphII graph = new CSGraphII();
        for (int i = 0; i < numCourses; i++) {
            CSNodeII node = new CSNodeII(i);
            graph.csNodes.add(node);
        }
        for (int row = 0; row < prerequisites.length; row++) {
            int prereqCourseNum = prerequisites[row][1];
            int courseNum = prerequisites[row][0];
            graph.csNodes.get(prereqCourseNum).prerequisiteOf.add(graph.csNodes.get(courseNum));
        }

        Stack<CSNodeII> stack = new Stack<>();
        boolean hasNoCycle = true;
        for (CSNodeII csNodeII : graph.csNodes) {
            if (!csNodeII.isTraversed) {
                if (!findOrderHelper(csNodeII, new HashSet<>(), stack)) {
                    hasNoCycle = false;
                    break;
                }
            }
        }

        if (hasNoCycle) {
            int[] ans = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                ans[i] = stack.pop().val;
            }
            return ans;
        } else {
            return new int[0];
        }

    }

    public static boolean findOrderHelper(CSNodeII root, Set<CSNodeII> visitedNodes, Stack<CSNodeII> stacks) {
        if (visitedNodes.contains(root)) {
            return false;
        }

        visitedNodes.add(root);

        for (CSNodeII csNodeII : root.prerequisiteOf) {
            if (!csNodeII.isTraversed) {
                if (!findOrderHelper(csNodeII, visitedNodes, stacks)) {
                    return false;
                }
            }
        }

        visitedNodes.remove(root);
        root.isTraversed = true;
        stacks.add(root);
        return true;
    }
}
