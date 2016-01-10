package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 For example,
 Given words = ["oath","pea","eat","rain"] and board =

 [
 ['o','a','a','n'],
 ['e','t','a','e'],
 ['i','h','k','r'],
 ['i','f','l','v']
 ]
 Return ["eat","oath"].
 Note:
 You may assume that all inputs are consist of lowercase letters a-z.

 click to show hint.

 You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

 If the current candidate does not exist in all words' prefix, you could stop backtracking immediately.
 What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not?
 How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem:
 Implement Trie (Prefix Tree) first.
 *
 * Created by Thanakorn on 1/10/16.
 */

class WS2TrieNode {

    WS2TrieNode[] charNodes;
    boolean hasWord;

    WS2TrieNode() {
        charNodes = new WS2TrieNode[26];
        hasWord = false;
    }

    void insert(String word) {
        char[] charArray = word.toCharArray();
        WS2TrieNode root = this;
        for (char c : charArray) {
            if (root.charNodes[c - 'a'] == null) {
                root.charNodes[c - 'a'] = new WS2TrieNode();
            }
            root = root.charNodes[c - 'a'];
        }
        root.hasWord = true;
    }

    boolean search(String word) {
        char[] charArray = word.toCharArray();
        WS2TrieNode root = this;
        for (char c : charArray) {
            if (root.charNodes[c - 'a'] == null) {
                return false;
            }
            root = root.charNodes[c - 'a'];
        }
        return root.hasWord;
    }

    boolean startWith(String prefix) {
        char[] charArray = prefix.toCharArray();
        WS2TrieNode root = this;
        for (char c : charArray) {
            if (root.charNodes[c - 'a'] == null) {
                return false;
            }
            root = root.charNodes[c - 'a'];
        }
        return true;
    }
}

public class WordSearchII {

    private WS2TrieNode root = new WS2TrieNode();

    public List<String> findWords(char[][] board, String[] words) {
        List<String> foundWords = new ArrayList<>();

        if (board == null || board.length == 0 || board[0].length == 0
                || words == null || words.length == 0) {
            return foundWords;
        }

        for (String word : words) {
            root.insert(word);
        }

        final int ROW = board.length;
        final int COL = board[0].length;

        Set<String> addedWords = new HashSet<>();
        boolean[][] visited = new boolean[ROW][COL];
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (root.charNodes[board[row][col] - 'a'] != null) {
                    findWordsHelper(board, row, col, addedWords, "", visited);
                }
            }
        }

        foundWords.addAll(addedWords);
        return foundWords;
    }

    private void findWordsHelper(char[][] board, int row, int col,
                                 Set<String> addedWords, String str,
                                 boolean[][] visited) {

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        String newStr = str + board[row][col];

        if (!root.startWith(newStr)) {
            visited[row][col] = false;
            return;
        }

        if (root.search(newStr)) {
            addedWords.add(newStr);
        }

        findWordsHelper(board, row - 1, col, addedWords, newStr, visited);
        findWordsHelper(board, row + 1, col, addedWords, newStr, visited);
        findWordsHelper(board, row, col - 1, addedWords, newStr, visited);
        findWordsHelper(board, row, col + 1, addedWords, newStr, visited);

        visited[row][col] = false;
    }

}
