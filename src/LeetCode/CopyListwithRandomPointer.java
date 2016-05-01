package LeetCode;

import DataStructure.RandomListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

 Return a deep copy of the list.
 *
 * Created by Thanakorn on 5/1/16.
 */
public class CopyListwithRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        Map<RandomListNode, RandomListNode> memo = new HashMap<>();
        return copyHelper(head, memo);
    }

    private RandomListNode copyHelper(RandomListNode head, Map<RandomListNode, RandomListNode> memo) {
        if (memo.containsKey(head)) {
            return memo.get(head);
        }

        RandomListNode copiedHead = new RandomListNode(head.label);

        memo.put(head, copiedHead);

        if (head.random != null) {
            if (!memo.containsKey(head.random)) {
                memo.put(head.random, copyHelper(head.random, memo));
            }
            copiedHead.random = memo.get(head.random);
        }

        if (head.next != null) {
            if (!memo.containsKey(head.next)) {
                memo.put(head.next, copyHelper(head.next, memo));
            }
            copiedHead.next = memo.get(head.next);
        }

        return copiedHead;
    }

}
