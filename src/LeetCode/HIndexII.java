package LeetCode;

/**
 * Created by Thanakorn on 9/12/15.
 */
public class HIndexII {

    public static void main(String[] args) {

    }

    /**
     *The basic idea of this solution is to use binary search to find the minimum index such that

     citations[index] >= length(citations) - index

     After finding this index, the answer is length(citations) - index.
     */
    public int hIndex(int[] citations) {
        int numPapers = citations.length;
        int left = 0, right = citations.length-1;

        while (left < right) {
            int curIndex = left + (right - left) / 2;
            int numPaper = numPapers - curIndex;
            if (citations[curIndex] == numPaper) {
                return numPaper;
            } else if (citations[curIndex] > numPaper) {
                right = curIndex;
            } else if (citations[curIndex] < numPaper) {
                left = curIndex+1;
            }
        }
        
        if (citations[left] < numPapers - left) {
            return 0;
        }
        return numPapers - left;
    }
}
