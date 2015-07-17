import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thanakorn on 7/16/15.
 */
public class TwoListsIntersection {

    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(1,2,3,5,1);
        List<Integer> l2 = Arrays.asList(1,2,4,4,5);
        List<Integer> intersection = getIntersection(l1, l2);

        intersection.forEach(x -> System.out.println(x));

    }

    public static List<Integer> getIntersection(List<Integer> l1, List<Integer> l2) {
        List<Integer> intersection = new ArrayList<>();

        if (l1 == null && l2 == null) {
            return intersection;
        }

        int l1P = 0, l2P = 0;

        while (l1P < l1.size() && l2P < l2.size()) {
            if (l1.get(l1P) == l2.get(l2P)) {
                intersection.add(l1.get(l1P));
                l1P++;
                l2P++;
            } else {
                break;
            }
        }

        return intersection;
    }
}
