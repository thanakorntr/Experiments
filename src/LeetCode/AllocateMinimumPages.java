package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=448
 *
 * You are given N number of books. Every ith book has Pi number of pages.
 You have to allocate books to M number of students so that maximum number of pages alloted to a student is minimum. A book will be allocated to exactly one student. Each student has to be allocated atleast one book.

 Note: Return -1 if a valid assignment is not possible, and allotment should be in contiguous order.

 Input:

 The first line contains 'T' denoting the number of testcases. Then follows description of T testcases:
 Each case begins with a single positive integer N denoting the number of books.
 The second line contains N space separated positive integers denoting the pages of each book.
 And the third line contains another integer M, denoting the number of students.


 Output:
 For each test case, output a single line containing minimum number of pages each student has to read for corresponding test case.


 Constraints:
 1<= T <=70
 1<= N <=50
 1<= A [ i ] <=250
 1<= M <=50

 Example:

 Input:
 1
 4
 12 34 67 90
 2

 Output:
 113

 Explaination: Allocation can be done in following ways:

 {12} and {34, 67, 90}     Maximum Pages = 191

 {12, 34} and {67, 90}     Maximum Pages = 157

 {12, 34, 67} and {90}        Maximum Pages = 113

 Therefore, the minimum of these cases is 113, which is selected as output.

 **For More Examples Use Expected Output**
 *
 * Created by thanakorntrakarnvanich on 4/5/16.
 */
public class AllocateMinimumPages {

    public static void main(String[] args) {
        int numStudents = 2;
        int[] numPages = new int[]{12, 34, 67, 90};
        System.out.println(new AllocateMinimumPages().minimumMaximumPage(numPages, numStudents));
    }

    public int minimumMaximumPage(int[] pages, int numStudents) {
        if (pages == null || pages.length == 0 || numStudents > pages.length) {
            return -1;
        }
        List<List<Integer>> allocations = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            allocations.add(new ArrayList<>());
        }
        return minimumMaximumPageHelper(0, 0, pages, numStudents, allocations);
    }

    private int minimumMaximumPageHelper(int startPageIndex, int startStudentIndex, int[] pages, int numStudents, List<List<Integer>> allocations) {
        if (startStudentIndex == numStudents - 1) {  // allocate all remaining pages to the last student
            for (int pageIndex = startPageIndex; pageIndex < pages.length; pageIndex++) {
                allocations.get(startStudentIndex).add(pages[pageIndex]);
            }
            int maxPages = evalMaxPages(allocations);
            for (int pageIndex = startPageIndex; pageIndex < pages.length; pageIndex++) {
                allocations.get(startStudentIndex).remove(allocations.get(startStudentIndex).size() - 1);
            }
            return maxPages;
        }

        if (pages.length - startPageIndex == numStudents - startStudentIndex) {
            int studentIndex = startStudentIndex;
            for (int pageIndex = startPageIndex; pageIndex < pages.length; pageIndex++) {
                allocations.get(studentIndex).add(pages[pageIndex]);
                studentIndex++;
            }
            int maxPages = evalMaxPages(allocations);
            studentIndex = startStudentIndex;
            for (int pageIndex = startPageIndex; pageIndex < pages.length; pageIndex++) {
                allocations.get(studentIndex).remove(allocations.get(studentIndex).size() - 1);
                studentIndex++;
            }
            return maxPages;
        }

        allocations.get(startStudentIndex).add(pages[startPageIndex]);
        int max1 = minimumMaximumPageHelper(startPageIndex + 1, startStudentIndex + 1, pages, numStudents, allocations);
        int max2 = minimumMaximumPageHelper(startPageIndex + 1, startStudentIndex, pages, numStudents, allocations);
        return Math.min(max1, max2);
    }

    /**
     * Evaluate maximum number of pages assigned to any student.
     */
    private int evalMaxPages(List<List<Integer>> allocations) {
        int maxPages = Integer.MIN_VALUE;
        for (List<Integer> allocation : allocations) {
            if (!allocation.isEmpty()) {
                int sum = 0;
                for (int i : allocation) {
                    sum += i;
                }
                maxPages = Math.max(maxPages, sum);
            }
        }
        return maxPages;
    }

}

