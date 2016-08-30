package LeetCode;

import java.util.*;

/**
 * Suppose we abstract our file system by a string in the following manner:

 The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

 dir
 subdir1
 subdir2
 file.ext
 The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

 The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

 dir
 subdir1
 file1.ext
 subsubdir1
 subdir2
 subsubdir2
 file2.ext
 The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

 We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

 Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

 Note:
 The name of a file contains at least a . and an extension.
 The name of a directory or sub-directory will not contain a ..
 Time complexity required: O(n) where n is the size of the input string.

 Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 *
 * Created by Thanakorn on 8/29/16.
 */
public class LongestAbsoluteFilePath {

    public static void main(String[] args) {

        LongestAbsoluteFilePath longestAbsoluteFilePath = new LongestAbsoluteFilePath();

        String input = "dir\\n\\tsubdir1\\n\\tsubdir2\\n\\t\\tfile.ext";
        System.out.println(longestAbsoluteFilePath.lengthLongestPath(input));  // 20

        input = "dir\\n\\tsubdir1\\n\\t\\tfile1.ext\\n\\t\\tsubsubdir1\\n\\tsubdir2\\n\\t\\tsubsubdir2\\n\\t\\t\\tfile2.ext";
        System.out.println(longestAbsoluteFilePath.lengthLongestPath(input));  // 32

        input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        System.out.println(longestAbsoluteFilePath.lengthLongestPath(input));  // 20

        input = "a.txt";
        System.out.println(longestAbsoluteFilePath.lengthLongestPath(input));  // 5

        input = "a\n\tb\n\t\tc.txt";
        System.out.println(longestAbsoluteFilePath.lengthLongestPath(input));  // 9

        input = "a\n\tb.txt\na2\n\tb2.txt";
        System.out.println(longestAbsoluteFilePath.lengthLongestPath(input)); // 9
    }

    public int lengthLongestPath(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        List<Integer> levelLen = new ArrayList<>();

        int maxLen = 0;
        int left = 0;
        int curLevel = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\\' || input.charAt(i) == '\n') {
                if (isFile(input, left, i - 1)) {
                    int fileLen = i - left;
                    int totalLen = fileLen + levelLen.get(curLevel - 1) + curLevel;
                    maxLen = Math.max(maxLen, totalLen);
                } else {
                    if (levelLen.isEmpty()) {
                        levelLen.add(i - left);
                    } else {
                        if (curLevel == levelLen.size()) {
                            levelLen.add(levelLen.get(curLevel - 1) + i - left);
                        } else {
                            if (curLevel != 0) {
                                levelLen.set(curLevel, levelLen.get(curLevel - 1) + i - left);
                            } else {
                                levelLen.set(0, i - left);
                            }
                        }
                    }
                }

                int numTab = 0;
                int nextIndex = input.charAt(i) == '\\' ? i + 2 : i + 1;
                for (int j = nextIndex; j < input.length(); j++) {
                    if ((input.charAt(j) == '\\' && j + 1 < input.length()
                            && input.charAt(j + 1) == 't')
                            || input.charAt(j) == '\t') {
                        numTab++;
                        if (input.charAt(j) == '\\') {
                            j++;
                        }
                    } else {
                        nextIndex = j;
                        break;
                    }
                }
                curLevel = numTab;
                left = nextIndex;
                i = left;
            }
        }

        if (isFile(input, left, input.length() - 1)) {
            int fileLen = input.length() - left;
            if (curLevel - 1 >= 0 && curLevel - 1 < levelLen.size()) {
                int totalLen = fileLen + levelLen.get(curLevel - 1) + curLevel;
                maxLen = Math.max(maxLen, totalLen);
            } else {
                maxLen = fileLen;
            }
        }


        return maxLen;
    }

    private boolean isFile(String input, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (input.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }

}
