package LeetCode;

import java.util.*;

/**
 * Created by Thanakorn on 6/22/15.
 */

class Vertex {

    public String word;
    public Set<String> visited;

    public Vertex(String word) {
        this.word = word;
        visited = new HashSet<>();
        visited.add(word);
    }

    public Vertex(String newWord, Vertex v) {
        this.word = newWord;
        this.visited = new HashSet<>();
        this.visited.addAll(v.visited);
        this.visited.add(newWord);
    }
}

public class WordLadder {


    public static void main(String[] args) {
        String begin = "nape", end = "mild";
        String[] arr = {"dose","ends","dine","jars","prow","soap","guns","hops","cray","hove","ella","hour","lens","jive","wiry","earl","mara","part","flue","putt","rory","bull","york","ruts","lily","vamp","bask","peer","boat","dens","lyre","jets","wide","rile","boos","down","path","onyx","mows","toke","soto","dork","nape","mans","loin","jots","male","sits","minn","sale","pets","hugo","woke","suds","rugs","vole","warp","mite","pews","lips","pals","nigh","sulk","vice","clod","iowa","gibe","shad","carl","huns","coot","sera","mils","rose","orly","ford","void","time","eloy","risk","veep","reps","dolt","hens","tray","melt","rung","rich","saga","lust","yews","rode","many","cods","rape","last","tile","nosy","take","nope","toni","bank","jock","jody","diss","nips","bake","lima","wore","kins","cult","hart","wuss","tale","sing","lake","bogy","wigs","kari","magi","bass","pent","tost","fops","bags","duns","will","tart","drug","gale","mold","disk","spay","hows","naps","puss","gina","kara","zorn","boll","cams","boas","rave","sets","lego","hays","judy","chap","live","bahs","ohio","nibs","cuts","pups","data","kate","rump","hews","mary","stow","fang","bolt","rues","mesh","mice","rise","rant","dune","jell","laws","jove","bode","sung","nils","vila","mode","hued","cell","fies","swat","wags","nate","wist","honk","goth","told","oise","wail","tels","sore","hunk","mate","luke","tore","bond","bast","vows","ripe","fond","benz","firs","zeds","wary","baas","wins","pair","tags","cost","woes","buns","lend","bops","code","eddy","siva","oops","toed","bale","hutu","jolt","rife","darn","tape","bold","cope","cake","wisp","vats","wave","hems","bill","cord","pert","type","kroc","ucla","albs","yoko","silt","pock","drub","puny","fads","mull","pray","mole","talc","east","slay","jamb","mill","dung","jack","lynx","nome","leos","lade","sana","tike","cali","toge","pled","mile","mass","leon","sloe","lube","kans","cory","burs","race","toss","mild","tops","maze","city","sadr","bays","poet","volt","laze","gold","zuni","shea","gags","fist","ping","pope","cora","yaks","cosy","foci","plan","colo","hume","yowl","craw","pied","toga","lobs","love","lode","duds","bled","juts","gabs","fink","rock","pant","wipe","pele","suez","nina","ring","okra","warm","lyle","gape","bead","lead","jane","oink","ware","zibo","inns","mope","hang","made","fobs","gamy","fort","peak","gill","dino","dina","tier"};
        Set<String> wordDict = new HashSet<>();
        for (String word : arr) {
            wordDict.add(word);
        }
        long t0 = System.currentTimeMillis();
        System.out.println(ladderLength(begin, end, wordDict));
        long t1 = System.currentTimeMillis();
        System.out.println((t1-t0));
    }


    public static int ladderLengthOptimized(String beginWord, String endWord, Set<String> wordDict) {
        wordDict.add(endWord);

        int numLevel = 1;
        Queue<String> currentLevel = new LinkedList<>();
        currentLevel.add(beginWord);

        while (!currentLevel.isEmpty()) {
            Queue<String> nextLevel = new LinkedList<>();
            while (!currentLevel.isEmpty()) {
                String curWord = currentLevel.poll();
                if (curWord.equals(endWord)) {
                    return numLevel;
                }
                char[] charArray = curWord.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char temp = charArray[i];
                        charArray[i] = c;
                        String nextWord = String.copyValueOf(charArray);
                        charArray[i] = temp;
                        if (wordDict.contains(nextWord)) {
                            nextLevel.add(nextWord);
                            wordDict.remove(nextWord);
                        }
                    }
                }

            }
            currentLevel = nextLevel;
            numLevel++;
        }

        return 0;
    }


    public static int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        return ladderLengthHelperBFS(beginWord, endWord, wordDict);
    }

    public static int ladderLengthHelperBFS(String beginWord, String endWord, Set<String> wordDict) {
        wordDict.add(endWord);

        int ladderLen = 1;

        Queue<List<Vertex>> queue = new LinkedList<>();
        queue.add(Arrays.asList(new Vertex(beginWord)));

        while (!queue.isEmpty()) {
            List<Vertex> curLevel = queue.poll();
            List<Vertex> nextLevel = new ArrayList<>();

            for (Vertex v : curLevel) {
                String curWord = v.word;
                Set<String> visited = v.visited;
                if (curWord.equals(endWord)) {
                    return ladderLen;
                }
                List<Vertex> nextWords = getNextWords(v, wordDict);
                nextLevel.addAll(nextWords);
            }

            if (!nextLevel.isEmpty()) {
                queue.add(nextLevel);
                ladderLen++;
            } else {
                ladderLen = 0;
                break;
            }
        }

        return ladderLen;
    }

    public static List<Vertex> getNextWords(Vertex v, Set<String> wordDict) {
        List<Vertex> nextWords = new ArrayList<>();

        char[] charArray = v.word.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < 26; j++) {
                char temp = charArray[i];
                charArray[i] = (char)('a' + j);
                String newStr = String.copyValueOf(charArray);
                charArray[i] = temp;
                if (!v.visited.contains(newStr) && wordDict.contains(newStr)) {
                    Vertex nextWord = new Vertex(newStr, v);
                    nextWords.add(nextWord);
                }
            }
        }

        return nextWords;
    }


    public static int ladderLengthHelperDFS(String beginWord, String endWord, Set<String> wordDict, Map<String, Integer> ladLenMemo, Set<String> visitedWords) {

        if (beginWord.equals(endWord)) {
            ladLenMemo.put(beginWord, 1);
            return 1;
        }

        if (ladLenMemo.containsKey(beginWord)) {
            return ladLenMemo.get(beginWord);
        }

        visitedWords.add(beginWord);
        int minLen = Integer.MAX_VALUE;
        for (String nextWord : wordDict) {
            if (!visitedWords.contains(nextWord) && isOneEditDistance(beginWord, nextWord)) {
                int len = -1;
                if (ladLenMemo.containsKey(nextWord)) {
                    len = 1 + ladLenMemo.get(nextWord);
                } else {
                    int tempLen = ladderLengthHelperDFS(nextWord, endWord, wordDict, ladLenMemo, visitedWords);
                    ladLenMemo.put(nextWord, tempLen);
                    len = 1 + tempLen;
                }
                minLen = Math.min(minLen, len);
            }
        }

        minLen = minLen == Integer.MAX_VALUE ? 0 : minLen;

        ladLenMemo.put(beginWord, minLen);
        return minLen;
    }

    public static boolean isOneEditDistance(String w1, String w2) {
        int numDiff = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                numDiff++;
                if (numDiff > 1) {
                    return false;
                }
            }
        }
        return numDiff == 1;
    }
}
