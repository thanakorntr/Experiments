import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Thanakorn on 6/27/15.
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        List<String> parens = generateParenthesis(3);

        for (String s : parens) {
            System.out.println(s);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        if (n < 1) {
            return ans;
        }

        if (n == 1) {
            ans.add("()");
            return ans;
        }

        List<String> previousSol = generateParenthesis(n-1);
        Set<String> history = new HashSet<>();
        for (String parens : previousSol) {
            List<String> insertedParens = insertParenthesis(parens);
            for (String insertedParen : insertedParens) {
                if (!history.contains(insertedParen)) {
                    ans.add(insertedParen);
                    history.add(insertedParen);
                }
            }
        }

        return ans;
    }

    public static List<String> insertParenthesis(String parens) {
        Set<String> history = new HashSet<>();
        List<String> ans = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append("()").append(parens);
        history.add(sb.toString());
        ans.add(sb.toString());

        sb = new StringBuilder();
        sb.append(parens).append("()");
        if (!history.contains(sb.toString())) {
            history.add(sb.toString());
            ans.add(sb.toString());
        }

        for (int i = 0; i < parens.length(); i++) {
            if (parens.charAt(i) == '(') {
                sb = new StringBuilder();
                sb.append(parens.substring(0, i+1)).append("()").append(parens.substring(i+1));
                if (!history.contains(sb.toString())) {
                    history.add(sb.toString());
                    ans.add(sb.toString());
                }
            }
        }

        return ans;
    }
}
