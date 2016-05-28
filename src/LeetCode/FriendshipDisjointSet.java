package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/disjoint-set-data-structures-java-implementation/
 *
 * Disjoint Set Data Structures (Java Implementation)
 Consider a situation with a number of persons and following tasks to be performed on them.

 Add a new friendship relation, i.e., a person x becomes friend of another person y.
 Find whether individual x is a friend of individual y (direct or indirect friend)
 *
 * Created by Thanakorn on 5/28/16.
 */
class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }

}

public class FriendshipDisjointSet {

    private Map<Person, Person> personRootMap = new HashMap<>();
    private Map<Person, Integer> personRankMap = new HashMap<>();

    public void add(Person person) {
        if (person != null && !personRootMap.containsKey(person)) {
            personRootMap.put(person, person);
            personRankMap.put(person, 0);
        }
    }

    public void addFriendship(Person person1, Person person2) {
        if (person1 != null && person2 != null && person1 != person2
                && personRootMap.containsKey(person1) && personRootMap.containsKey(person2)) {

            Person parent1 = getTopParent(person1);
            Person parent2 = getTopParent(person2);
            if (parent1 != parent2) {
                int rank1 = personRankMap.get(parent1);
                int rank2 = personRankMap.get(parent2);
                if (rank1 < rank2) {
                    personRootMap.put(parent1, parent2);
                } else if (rank1 > rank2) {
                    personRootMap.put(parent2, parent1);
                } else {
                    personRootMap.put(parent1, parent2);
                    personRankMap.put(parent2, rank2 + 1);
                }
            }
        }
    }

    public boolean isFriend(Person person1, Person person2) {
        if (person1 != null && person2 != null && personRootMap.containsKey(person1)
                && personRootMap.containsKey(person2)) {
            return getTopParent(person1) == getTopParent(person2);
        }
        return false;
    }

    private Person getTopParent(Person person) {
        if (person == null || !personRootMap.containsKey(person)) {
            return null;
        }
        if (personRootMap.get(person) == person) {
            return person;
        }
        Person parent = getTopParent(personRootMap.get(person));
        personRootMap.put(person, parent);
        return parent;
    }

}
