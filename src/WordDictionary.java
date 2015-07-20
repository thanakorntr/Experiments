/**
 * Created by Thanakorn on 7/19/15.
 */
public class WordDictionary {

    private TrieNode root = new TrieNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curNode.children[c-'a'] == null) {
                curNode.children[c-'a'] = new TrieNode();
            }
            curNode = curNode.children[c-'a'];
        }
        curNode.hasWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        TrieNode curNode = root;
        return searchHelper(word, root);
    }

    private boolean searchHelper(String word, TrieNode root) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for (TrieNode tn : curNode.children) {
                    if (tn != null) {
                        boolean tmp = searchHelper(word.substring(i+1), tn);
                        if (tmp) {
                            return true;
                        }
                    }
                }
                return false;
            } else {
                if (curNode.children[c-'a'] == null) {
                    return false;
                }
                curNode = curNode.children[c-'a'];
            }
        }
        return curNode.hasWord;
    }

    public static void main(String[] args) {

        WordDictionary wordDictionary = new WordDictionary();

        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // false
        System.out.println(wordDictionary.search("bad")); // true
        System.out.println(wordDictionary.search(".ad")); // true
        System.out.println(wordDictionary.search("b..")); // true


    }
}
