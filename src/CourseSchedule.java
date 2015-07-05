import java.util.*;

/**
 * Created by Thanakorn on 7/4/15.
 */

class CSNode {
    int val;
    List<CSNode> prerequisiteOf;
    boolean provenHasNoCycle;

    CSNode(int val) {
        this.val = val;
        prerequisiteOf = new ArrayList<>();
        provenHasNoCycle = false;
    }
}

class CSGraph {
    List<CSNode> csNodes;

    CSGraph() {
        csNodes = new ArrayList<>();
    }
}

public class CourseSchedule {

    public static void main(String[] args) {

        int numCourses = 2;
        int[][] prerequisites = { {1,0} };   // course 0 is a prerequisite of course 1
        System.out.println(canFinish(numCourses, prerequisites));  // true

        prerequisites = new int[][]{ {1,0}, {0,1} };
        System.out.println(canFinish(numCourses, prerequisites));  // false

        prerequisites = new int[][] { {1,0}, {2,0}, {3,1}, {3,2}};  // true
        numCourses = 4;
        System.out.println(canFinish(numCourses, prerequisites));

        prerequisites = new int[][] { {1,0}, {2,1}, {0,2}};  // false
        numCourses = 3;
        System.out.println(canFinish(numCourses, prerequisites));

    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // initialize all nodes and put them in a graph
        CSGraph graph = new CSGraph();
        for (int i = 0; i < numCourses; i++) {
            CSNode node = new CSNode(i);
            graph.csNodes.add(node);
        }

        for (int row = 0; row < prerequisites.length; row++) {
            int prereqCourseNum = prerequisites[row][1];
            int courseNum = prerequisites[row][0];
            graph.csNodes.get(prereqCourseNum).prerequisiteOf.add(graph.csNodes.get(courseNum));
        }

        for (CSNode node : graph.csNodes) {
            if (!node.provenHasNoCycle) {
                if (!canFinishHelper(node, new HashSet<>())) {
                    return false;
                }
            }
        }

        return true;
    }


    public static boolean canFinishHelper(CSNode root, Set<Integer> visitedCourses) {
        if (visitedCourses.contains(root.val)) {
            return false;
        }

        visitedCourses.add(root.val);

        for (CSNode nextNodes : root.prerequisiteOf) {
            if (!nextNodes.provenHasNoCycle) {
                if (!canFinishHelper(nextNodes, visitedCourses)) {
                    return false;
                }
            }
        }

        root.provenHasNoCycle = true;
        visitedCourses.remove(root.val);
        return true;
    }
}
