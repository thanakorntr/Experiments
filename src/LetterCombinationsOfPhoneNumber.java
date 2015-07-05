import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 6/28/15.
 */
public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {

        String digitStr = "23";

        List<String> combinations = letterCombinations(digitStr);

        System.out.println(combinations.toString());

    }

    public static List<String> letterCombinations(String digits) {
        return letterCombinationsHelper(digits, 0);
    }

    public static List<String> letterCombinationsHelper(String digits, int startIndex) {
        List<String> combinations = new ArrayList<>();
        if (digits.isEmpty() || startIndex == digits.length()) {
            return combinations;
        }

        String frontDigitStr = digits.substring(startIndex, startIndex+1);
        List<String> frontAlphabets = getCorrespondingAlphabets(frontDigitStr);

        List<String> combinationsNextPart = letterCombinationsHelper(digits, startIndex+1);
        if (!combinationsNextPart.isEmpty()) {
            for (String frontAlphabet : frontAlphabets) {
                for (String nextCombination : combinationsNextPart) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(frontAlphabet).append(nextCombination);
                    combinations.add(sb.toString());
                }
            }
        } else {
            combinations.addAll(frontAlphabets);
        }

        return combinations;
    }

    public static List<String> getCorrespondingAlphabets(String digit) {
        List<String> alphabets = new ArrayList<>();

        if (digit.equals("1")) {

        } else if (digit.equals("2")) {
            alphabets.add("a");
            alphabets.add("b");
            alphabets.add("c");
        } else if (digit.equals("3")) {
            alphabets.add("d");
            alphabets.add("e");
            alphabets.add("f");
        } else if (digit.equals("4")) {
            alphabets.add("g");
            alphabets.add("h");
            alphabets.add("i");
        } else if (digit.equals("5")) {
            alphabets.add("j");
            alphabets.add("k");
            alphabets.add("l");
        } else if (digit.equals("6")) {
            alphabets.add("m");
            alphabets.add("n");
            alphabets.add("o");
        } else if (digit.equals("7")) {
            alphabets.add("p");
            alphabets.add("q");
            alphabets.add("r");
            alphabets.add("s");
        } else if (digit.equals("8")) {
            alphabets.add("t");
            alphabets.add("u");
            alphabets.add("v");
        } else if (digit.equals("9")) {
            alphabets.add("w");
            alphabets.add("x");
            alphabets.add("y");
            alphabets.add("z");
        }

        return alphabets;
    }
}
