import java.util.Stack;

/**
 * Created by Thanakorn on 5/29/15.
 */
class MinStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty()) {
            if (x <= minStack.peek()) {
                minStack.push(x);
            }
        } else {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            stack.pop();
            minStack.pop();
        } else {
            stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(512);
        ms.push(-1024);
        ms.push(-1024);
        ms.push(512);
        ms.pop();
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.getMin());
    }
}
