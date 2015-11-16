package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 11/15/15.
 *
 * Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"
 click to show corner cases.

 Corner Cases:
 Did you consider the case where path = "/../"?
 In this case, you should return "/".
 Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 In this case, you should ignore redundant slashes and return "/home/foo".

 */
public class SimplifyPath {

    public static void main(String[] args) {

        String s = "/a/f//b";
        System.out.println(Arrays.toString(s.split("/")));

    }

    public String simplifyPath(String path) {

        Set<String> skipDirs = new HashSet<>();
        skipDirs.add("");
        skipDirs.add(".");

        Deque<String> dirs = new ArrayDeque<>();
        String[] slashSeparated = path.split("/");

        for (String dir : slashSeparated) {
            if (dir.equals("..")) {
                if (!dirs.isEmpty()) {
                    dirs.pollLast();
                }
            } else {
                if (!skipDirs.contains(dir)) {
                    dirs.add(dir);
                }
            }
        }

        StringBuilder simplifiedPath = new StringBuilder();
        for (String dir : dirs) {
            simplifiedPath.append("/").append(dir);
        }

        return simplifiedPath.length() == 0 ? "/" : simplifiedPath.toString();
    }
}
