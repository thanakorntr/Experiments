import java.util.*;

/**
 * Created by Thanakorn on 6/27/15.
 */
public class BasicCalculator {

    public static void main(String[] args) {
        String s1 = "1 + 1";
        s1 = " 2-1 + 2 ";
        s1 = "(1+(4+5+2)-3)+(6+8)";

        System.out.println(calculate(s1));
    }

    public static int calculate(String s) {

        List<String> processedStr = processString(s);
        Stack<Queue<String>> stackOfQueues = new Stack<>();

        for (String c : processedStr) {
            if (c.equals("(")) {
                stackOfQueues.add(new LinkedList<>());
            } else if (c.equals(")")) {
                int result = evalQueue(stackOfQueues.pop());
                if (stackOfQueues.isEmpty()) {
                    stackOfQueues.add(new LinkedList<>());
                }
                stackOfQueues.peek().add(Integer.toString(result));
            } else {
                stackOfQueues.peek().add(c);
            }
        }

        return evalQueue(stackOfQueues.pop());
    }

    public static List<String> processString(String s) {
        List<String> ans = new ArrayList<>();

        ans.add("(");

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == ')' || c == '+' || c == '-') {
                ans.add(Character.toString(c));
            } else if (Character.isDigit(c)) {
                int end = i + 1;
                while (end < s.length() && Character.isDigit(s.charAt(end))) {
                    end++;
                }
                ans.add(s.substring(i, end));
                i = end - 1;
            }
        }

        ans.add(")");

        return ans;
    }

    public static int evalQueue(Queue<String> s) {
        if (s.size() == 0) return 0;
        if (s.size() == 1) return Integer.parseInt(s.poll());

        int result = Integer.parseInt(s.poll());

        while (!s.isEmpty()) {
            String operator = s.poll();
            int secondOperand = Integer.parseInt(s.poll());
            if (operator.equals("+")) {
                result += secondOperand;
            } else if (operator.equals("-")) {
                result -= secondOperand;
            }
        }

        return result;
    }
}
