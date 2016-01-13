package LeetCodeII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the word list
 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 Subscribe to see which companies asked this question
 *
 * Created by Thanakorn on 1/10/16.
 */
public class WordLadder {


    public static void main(String[] args) {
        String begin = "a";
        String end = "c";
        Set<String> set = new HashSet<>();
        set.add("a"); set.add("b"); set.add("c");
        new WordLadder().ladderLength(begin, end, set);
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {

        wordList.add(endWord);

        int count = 1;

        List<String> curLevel = new ArrayList<>();
        curLevel.add(beginWord);
        wordList.remove(beginWord);

        while (!curLevel.isEmpty()) {
            List<String> nextLevel = new ArrayList<>();
            for (String word : curLevel) {
                if (word.equals(endWord)) {
                    return count;
                }
                char[] charArray = word.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    char tmp = charArray[i];
                    for (int j = 0; j < 25; j++) {
                        charArray[i] = (char)('a' + j);
                        String newStr = String.valueOf(charArray);
                        if (wordList.contains(newStr)) {
                            nextLevel.add(newStr);
                            wordList.remove(newStr);
                        }
                    }
                    charArray[i] = tmp;
                }
            }

            count++;
            curLevel = nextLevel;
        }

        return 0;
    }

}
