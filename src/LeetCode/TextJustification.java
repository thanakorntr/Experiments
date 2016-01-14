package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 *  Given an array of words and a length L, format the text such that each line has exactly L 
 *  characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
 Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible. 
 If the number of spaces on a line do not divide evenly between words, 
 the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:

 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]

 Note: Each word is guaranteed not to exceed L in length.

 click to show corner cases.
 Corner Cases:

 A line other than the last line might contain only one word. What should you do in this case?
 In this case, that line should be left-justified.
 *
 * Created by Thanakorn on 1/12/16.
 */
public class TextJustification {

    public static void main(String[] args) {
        String[] words = new String[]{"What", "must", "be", "shall", "be."};
        int maxWidth = 12;
        new TextJustification().fullJustify(words, maxWidth);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> justifiedTexts = new ArrayList<>();

        if (words == null || words.length == 0) {
            return justifiedTexts;
        }

        return justifyHelper(words, 0, maxWidth);
    }

    private List<String> justifyHelper(String[] words, int startIndex, int maxWidth) {

        List<String> justifiedTexts = new ArrayList<>();

        if (startIndex >= words.length) {
            return justifiedTexts;
        } else if (startIndex == words.length - 1) {
            justifiedTexts.add(justifyLine(words, startIndex, words.length - 1, maxWidth));
            return justifiedTexts;
        }

        int minLen = 0;
        int endIndex = -1;
        for (int i = startIndex; i < words.length; i++) {
            minLen += words[i].length();
            if (i != startIndex) {
                minLen++;
            }
            if (minLen > maxWidth) {
                endIndex = i - 1;
                break;
            }
            if (i == words.length - 1) {
                endIndex = words.length - 1;
            }
        }

        String justifiedLine = justifyLine(words, startIndex, endIndex, maxWidth);
        justifiedTexts.add(justifiedLine);

        List<String> nextJustifiedTexts = justifyHelper(words, endIndex + 1, maxWidth);
        justifiedTexts.addAll(nextJustifiedTexts);
        return justifiedTexts;
    }

    private String justifyLine(String[] words, int startIndex, int endIndex, int maxWidth) {

        StringBuilder stringBuilder = new StringBuilder();

        if (endIndex == words.length - 1) {  // last line
            for (int i = startIndex; i <= endIndex; i++) {
                stringBuilder.append(words[i]);
                if (i != endIndex) {
                    stringBuilder.append(" ");
                }
            }
            while (stringBuilder.length() != maxWidth) {
                stringBuilder.append(" ");
            }
            return stringBuilder.toString();
        } else {  // not last line
            if (startIndex == endIndex) {  // corner case where line contains just one word
                stringBuilder.append(words[startIndex]);
                while (stringBuilder.length() != maxWidth) {
                    stringBuilder.append(" ");
                }
                return stringBuilder.toString();
            } else {
                int allWordLen = 0;
                for (int i = startIndex; i <= endIndex; i++) {
                    allWordLen += words[i].length();
                }
                int numRemainingSpaces = maxWidth - allWordLen;
                int[] numSpaces = new int[endIndex - startIndex];
                int spaceIndex = 0;
                while (numRemainingSpaces > 0) {
                    numSpaces[spaceIndex % numSpaces.length]++;
                    spaceIndex++;
                    numRemainingSpaces--;
                }

                spaceIndex = 0;
                for (int i = startIndex; i <= endIndex; i++) {
                    stringBuilder.append(words[i]);
                    if (i != endIndex) {
                        int spaceToAdd = numSpaces[spaceIndex];
                        while (spaceToAdd > 0) {
                            stringBuilder.append(" ");
                            spaceToAdd--;
                        }
                    }
                    spaceIndex++;
                }

                return stringBuilder.toString();
            }
        }
    }

}
