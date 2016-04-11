package LeetCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 5/28/15.
 */
public class Test {

    public static void main(String[] args) throws IOException {

        List<String> list = new ArrayList<>();
        list.sort((s1, s2) -> {
            if (s1.length() == s2.length()) {
                return 0;
            }
            return -1;
        });
    }

}
