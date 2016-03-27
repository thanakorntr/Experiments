package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find all shortest transformation sequence(s) from beginWord to endWord,
 * such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the word list
 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]
 Return
 [
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
 ]
 Note:
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 *
 * Created by Thanakorn on 1/10/16.
 */

class WL2Container {

    List<String> path;
    Set<String> visitedWords;

    WL2Container() {
        path = new ArrayList<>();
        visitedWords = new HashSet<>();
    }

    WL2Container(WL2Container wl2Container) {
        path = new ArrayList<>(wl2Container.path);
        visitedWords = new HashSet<>(wl2Container.visitedWords);
    }
}

public class WordLadderII {


    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {

        wordList.add(endWord);

        List<List<String>> allLadders = new ArrayList<>();

        List<WL2Container> curLevelPaths = new ArrayList<>();
        WL2Container initial = new WL2Container();
        initial.path.add(beginWord);
        initial.visitedWords.add(beginWord);
        curLevelPaths.add(initial);

        while (!curLevelPaths.isEmpty()) {
            List<WL2Container> nextLevelPaths = new ArrayList<>();

            for (WL2Container wl2Container : curLevelPaths) {
                String lastWord = wl2Container.path.get(wl2Container.path.size() - 1);
                if (lastWord.equals(endWord)) {
                    allLadders.add(wl2Container.path);
                } else {
                    if (!allLadders.isEmpty()) {
                        continue;
                    }
                    char[] charArray = lastWord.toCharArray();
                    for (int i = 0; i < charArray.length; i++) {
                        char tmpChar = charArray[i];
                        for (int j = 0; j < 25; j++) {
                            charArray[i] = (char)('a' + j);
                            String newStr = String.valueOf(charArray);
                            if (wordList.contains(newStr) && !wl2Container.visitedWords.contains(newStr)) {
                                WL2Container derivedWL2Container = new WL2Container(wl2Container);
                                derivedWL2Container.path.add(newStr);
                                derivedWL2Container.visitedWords.add(newStr);
                                nextLevelPaths.add(derivedWL2Container);
                            }
                        }
                        charArray[i] = tmpChar;
                    }
                }
            }

            if (!allLadders.isEmpty()) {
                return allLadders;
            }
            curLevelPaths = nextLevelPaths;
        }

        return allLadders;
    }
}
