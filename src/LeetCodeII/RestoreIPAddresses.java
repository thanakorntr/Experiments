package LeetCodeII;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 For example:
 Given "25525511135",

 return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 *
 * Created by Thanakorn on 3/27/16.
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        return restoreIpAddressesHelper(s, 0, 3);
    }

    private List<String> restoreIpAddressesHelper(String s, int startIndex, int remainingDot) {
        List<String> ips = new ArrayList<>();

        if (startIndex >= s.length()) {
            return ips;
        }

        if (remainingDot == 0) {
            if (startIndex < s.length()) {
                if (isValidNum(s, startIndex, s.length() - 1)) {
                    ips.add(s.substring(startIndex));
                }
            }
            return ips;
        }

        for (int i = 0; i < 3; i++) {
            if (isValidNum(s, startIndex, startIndex + i)) {
                List<String> nextAddressParts = restoreIpAddressesHelper(s, startIndex + i + 1, remainingDot - 1);
                if (!nextAddressParts.isEmpty()) {
                    String curAddressPart = s.substring(startIndex, startIndex + i + 1);
                    curAddressPart += ".";
                    for (String nextAddress : nextAddressParts) {
                        ips.add(curAddressPart + nextAddress);
                    }
                }
            }
        }

        return ips;
    }

    private boolean isValidNum(String s, int start, int end) {
        if (start >= s.length() || end >= s.length()) {
            return false;
        }
        if (start == end) {
            return true;
        } else if (start + 1 == end) {
            return s.charAt(start) != '0';
        } else if (start + 2 == end){
            if (s.charAt(start) == '0') {
                return false;
            }
            if (s.charAt(start) >= '3') {
                return false;
            }
            if (s.charAt(start) == '2') {
                if (s.charAt(start + 1) >= '6') {
                    return false;
                } else if (s.charAt(start + 1) == '5') {
                    if (s.charAt(end) > '5') {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String num = "240102";
        List<String> ips = new RestoreIPAddresses().restoreIpAddresses(num);
        System.out.println(ips.toString());
    }
}
