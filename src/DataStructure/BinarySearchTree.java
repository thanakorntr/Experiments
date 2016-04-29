package DataStructure;

/**
 * http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 *
 * Created by Thanakorn on 4/23/16.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private static class BSTNode<T extends Comparable<T>> {
        public T data;
        public BSTNode<T> leftNode;
        public BSTNode<T> rightNode;
        public BSTNode<T> parent;

        public BSTNode(T data) {
            this.data = data;
        }
    }

    private BSTNode<T> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void insert(T object) {
        if (object == null) {
            return;
        }
        BSTNode<T> bstNode = new BSTNode<>(object);
        insertHelper(bstNode);
    }

    private void insertHelper(BSTNode<T> bstNode) {
        if (root == null) {
            root = bstNode;
            size++;
            return;
        }

        BSTNode<T> curNode = root;
        while (true) {
            int compare = bstNode.data.compareTo(curNode.data);
            if (compare == 0) {
                return;
            } else if (compare < 0) {
                if (curNode.leftNode == null) {
                    curNode.leftNode = bstNode;
                    bstNode.parent = curNode;
                    size++;
                    return;
                }
                curNode = curNode.leftNode;
            } else {
                if (curNode.rightNode == null) {
                    curNode.rightNode = bstNode;
                    bstNode.parent = curNode;
                    size++;
                    return;
                }
                curNode = curNode.rightNode;
            }
        }
    }

    public void delete(T object) {
        if (root == null || object == null) {
            return;
        }
        BSTNode<T> bstNode = new BSTNode<>(object);
        deleteHelper(bstNode);
    }

    private void deleteHelper(BSTNode<T> bstNode) {
        BSTNode<T> curNode = root;
        while (curNode != null) {
            int compare = bstNode.data.compareTo(curNode.data);
            if (compare == 0) {
                if (curNode.leftNode == null && curNode.rightNode == null) {
                    if (curNode.parent == null) {
                        root = null;
                        return;
                    } else {
                        if (curNode.parent.leftNode.data.compareTo(curNode.data) == 0) {
                            curNode.parent.leftNode = null;
                        } else {
                            curNode.parent.rightNode = null;
                        }
                    }
                } else if (curNode.leftNode == null || curNode.rightNode == null) {
                    if (curNode.leftNode == null) {
                        if (curNode.parent == null) {
                            root = curNode.rightNode;
                            return;
                        }
                        curNode.rightNode.parent = curNode.parent;
                        curNode.parent.rightNode = curNode.rightNode;
                    } else {
                        if (curNode.parent == null) {
                            root = curNode.leftNode;
                            return;
                        }
                        curNode.leftNode.parent = curNode.parent;
                        curNode.parent.leftNode = curNode.leftNode;
                    }
                    return;
                } else {
                    BSTNode<T> replacementNode = getMinNode(curNode.rightNode);
                    if (replacementNode.parent.leftNode != null
                            && replacementNode.parent.leftNode.data.compareTo(replacementNode.data) == 0) {
                        replacementNode.parent.leftNode = null;
                    } else {
                        replacementNode.parent.rightNode = null;
                    }
                    curNode.data = replacementNode.data;
                }
            } else if (compare < 0) {
                curNode = curNode.leftNode;
            } else {
                curNode = curNode.rightNode;
            }
        }
    }

    private BSTNode<T> getMaxNode(BSTNode<T> bstNode) {
        if (bstNode == null) {
            return null;
        }
        while (bstNode.rightNode != null) {
            bstNode = bstNode.rightNode;
        }
        return bstNode;
    }

    private BSTNode<T> getMinNode(BSTNode<T> bstNode) {
        if (bstNode == null) {
            return null;
        }
        while (bstNode.leftNode != null) {
            bstNode = bstNode.leftNode;
        }
        return bstNode;
    }

    public boolean find(T object) {
        if (object == null) {
            return false;
        }
        BSTNode<T> bstNode = new BSTNode<>(object);
        return findHelper(bstNode);
    }

    private boolean findHelper(BSTNode<T> bstNode) {
        BSTNode<T> curNode = root;
        while (curNode != null) {
            int compare = bstNode.data.compareTo(curNode.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                curNode = curNode.leftNode;
            } else {
                curNode = curNode.rightNode;
            }
        }
        return false;
    }

    public void display() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

}
