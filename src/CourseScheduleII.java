/**
 * Created by Thanakorn on 7/25/15.
 */
public class CourseScheduleII {

    public static void main(String[] args) {

    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses < 1) {
            return new int[0];
        }

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

        

        return null;
    }
}
