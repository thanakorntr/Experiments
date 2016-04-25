package DataStructure;

import java.util.*;

/**
 * Created by Thanakorn on 4/23/16.
 */
public class MinHeap<T extends Comparable<T>> {

    private static int CAPACITY = 100;
    private Object[] container;
    private int nextindex = 0;

    public MinHeap() {
        container = new Object[CAPACITY];
    }

    private T get(int index) {
        return (T) container[index];
    }

    public void add(T object) {
        if (size() == container.length) {
            Object[] newContainer = new Object[CAPACITY * 2];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
            CAPACITY *= 2;
        }
        container[nextindex++] = object;
        minHeapifyUp(nextindex - 1);
    }

    // TODO
    public void remove(T object) {

    }

    private void remove(int index) {
        if (index < 0 || index >= container.length) {
            throw new IndexOutOfBoundsException();
        }
        swap(index, nextindex - 1);
        container[nextindex - 1] = null;
        nextindex--;
        minHeapifyDown(index);
        minHeapifyUp(index);
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T minObject = get(0);
        swap(0, nextindex - 1);
        container[nextindex - 1] = null;
        nextindex--;
        minHeapifyDown(0);
        return minObject;
    }

    public T peek() {
        return isEmpty() ? null : get(0);
    }

    public boolean isEmpty() {
        return nextindex == 0;
    }

    public int size() {
        return nextindex;
    }

    private void minHeapify() {
        for (int i = nextindex - 1; i >= 0; i--) {
            minHeapifyDown(i);
        }
    }

    private void minHeapifyDown(int curNodeIndex) {
        while (true) {
            T curNode = get(curNodeIndex);
            int leftChildIndex = getLeftChildIndex(curNodeIndex);
            int rightChildIndex = getRightChildIndex(curNodeIndex);
            if (leftChildIndex == -1 && rightChildIndex == -1) {
                return;
            }
            T minChild = curNode;
            int minChildIndex = curNodeIndex;
            if (leftChildIndex != -1) {
                T leftChild = get(leftChildIndex);
                if (leftChild.compareTo(curNode) < 0) {
                    minChild = leftChild;
                    minChildIndex = leftChildIndex;
                }
            }
            if (rightChildIndex != -1) {
                T rightChild = get(rightChildIndex);
                if (rightChild.compareTo(minChild) < 0) {
                    minChildIndex = rightChildIndex;
                }
            }
            if (minChildIndex == curNodeIndex) {
                return;
            }
            swap(curNodeIndex, minChildIndex);
            curNodeIndex = minChildIndex;
        }
    }

    private void minHeapifyUp(int curNodeIndex) {
        while (true) {
            T curNode = get(curNodeIndex);
            int parentIndex = getParentIndex(curNodeIndex);
            if (parentIndex == -1) {
                return;
            }
            T parentNode = get(parentIndex);
            if (parentNode.compareTo(curNode) <= 0) {
                return;
            }
            swap(curNodeIndex, parentIndex);
            curNodeIndex = parentIndex;
        }
    }

    private int getLeftChildIndex(int nodeIndex) {
        if (nodeIndex < 0 || nodeIndex >= container.length) {
            throw new IndexOutOfBoundsException();
        }
        int leftChildIndex = 2 * nodeIndex + 1;
        return leftChildIndex < container.length && container[leftChildIndex] != null ? leftChildIndex : -1;
    }

    private int getRightChildIndex(int nodeIndex) {
        if (nodeIndex < 0 || nodeIndex >= container.length) {
            throw new IndexOutOfBoundsException();
        }
        int rightChildIndex = 2 * nodeIndex + 2;
        return rightChildIndex < container.length && container[rightChildIndex] != null ? rightChildIndex : -1;
    }

    private int getParentIndex(int nodeIndex) {
        if (nodeIndex < 0 || nodeIndex >= container.length) {
            throw new IndexOutOfBoundsException();
        }
        if (nodeIndex == 0) {
            return -1;
        }
        int parentIndex = (nodeIndex - 1) / 2;
        return parentIndex >= 0 && container[parentIndex] != null ? parentIndex : -1;
    }

    public void swap(int firstIndex, int secondIndex) {
        if (firstIndex < 0 || firstIndex >= container.length || secondIndex < 0 || secondIndex >= container.length) {
            throw new IndexOutOfBoundsException();
        }
        T tmp = get(firstIndex);
        container[firstIndex] = container[secondIndex];
        container[secondIndex] = tmp;
    }


    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>();
        java.util.Queue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 1; i <= 10; i++) {
            int randomVal = 1 + (int)(2 * Math.random());
            minHeap.add(randomVal);
            priorityQueue.add(randomVal);
        }
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll());
        }
        priorityQueue.remove(1);
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }

}
