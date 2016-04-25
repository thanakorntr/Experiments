package DataStructure;

/**
 * Created by Thanakorn on 4/23/16.
 */
public class Queue<T> {

    private static int CAPACITY = 100;
    private Object[] container;
    private int nextIndex = 0;
    private int frontIndex = 0;
    private int count = 0;

    public Queue() {
        container = new Object[CAPACITY];
    }

    private T get(int index) {
        return (T) container[index];
    }

    public void add(T object) {
        if (size() == container.length) {
            Object[] newContainer = new Object[CAPACITY * 2];
            int tmpCount = 0;
            int tmpIndex = 0;
            while (tmpCount != count) {
                newContainer[tmpIndex] = get(frontIndex);
                tmpCount++;
                tmpIndex++;
                frontIndex = (frontIndex + 1) % container.length;
            }
            container = newContainer;
            frontIndex = 0;
            nextIndex = count;
            CAPACITY *= 2;
        }
        container[nextIndex] = object;
        nextIndex = (nextIndex + 1) % container.length;
        count++;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T front = get(frontIndex);
        container[frontIndex] = null;
        frontIndex = (frontIndex + 1) % container.length;
        count--;
        return front;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return get(frontIndex);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.add(1);
        queue.add(2);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        Queue<String> stringQueue = new Queue<>();
        stringQueue.add("T");
        stringQueue.add("V");
        while (!stringQueue.isEmpty()) {
            System.out.println(stringQueue.poll());
        }
    }

}
