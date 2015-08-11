package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 6/24/15.
 */
public class RestoreIPAddresses {


    public static void main(String[] args) {
        String str = "010010";

        List<String> ips = restoreIpAddresses(str);

        for (String ip : ips) {
            System.out.println(ip);
        }
    }

    public static List<String> restoreIpAddresses(String str) {
        return restoreIpAddressesHelper(str, 3);
    }

    public static List<String> restoreIpAddressesHelper(String str, int remainingDots) {
        List<String> ips = new ArrayList<>();

        if (remainingDots == 0) {
            if (isFrom0To255(str)) {
                ips.add(str);
            }
            return ips;
        }

        int frontPartLen = Math.min(3, str.length());

        for (int i = 0; i < frontPartLen; i++) {
            String frontPart = str.substring(0, i+1);
            if (isFrom0To255(frontPart)) {
                List<String> remainingParts = restoreIpAddressesHelper(str.substring(i+1), remainingDots - 1);
                for (String remainingPart : remainingParts) {
                    String validIp = frontPart + "." + remainingPart;
                    ips.add(validIp);
                }
            }
        }

        return ips;
    }

    public static boolean isFrom0To255(String str) {
        if (str.isEmpty() || (str.startsWith("0") && str.length() > 1)) return false;

        try {
            int num = Integer.parseInt(str);
            return num >= 0 && num <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
