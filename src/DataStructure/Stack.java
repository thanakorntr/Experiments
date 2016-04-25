package DataStructure;

import java.util.EmptyStackException;

/**
 * Created by Thanakorn on 4/23/16.
 */
public class Stack<T> {

    private static int CAPACITY = 100;
    private Object[] container;
    private int nextIndex = 0;

    public Stack() {
        container = new Object[CAPACITY];
    }

    private T get(int index) {
        return (T) container[index];
    }

    public void push(T object) {
        if (size() == container.length) {
            Object[] newContainer = new Object[CAPACITY * 2];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
            CAPACITY *= 2;
        }
        container[nextIndex++] = object;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T top = get(nextIndex - 1);
        nextIndex--;
        return top;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return get(nextIndex - 1);
    }

    public boolean isEmpty() {
        return nextIndex == 0;
    }

    public int size() {
        return nextIndex;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        Stack<String> stringStack = new Stack<>();
        stringStack.push("T");
        stringStack.push("b");
        while (!stringStack.isEmpty()) {
            System.out.println(stringStack.pop());
        }
    }

}
