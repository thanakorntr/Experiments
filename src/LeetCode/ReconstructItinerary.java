package LeetCode;

import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

 Note:
 If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 All airports are represented by three capital letters (IATA code).
 You may assume all tickets may form at least one valid itinerary.
 Example 1:
 tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 Example 2:
 tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 *
 * Created by Thanakorn on 2/6/16.
 */

class Ticket implements Comparator<Ticket> {

    public String from;
    public String to;

    public Ticket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.to.compareTo(o2.to);
    }
}

public class ReconstructItinerary {

    public static void main(String[] args) {

//        PriorityQueue<String> pq = new PriorityQueue<>();
//        pq.add("C");
//        pq.add("A");
//        pq.add("B");
//        for (String p : pq) {
//            System.out.println(p);
//        }

        String[][] tickets = new String[][]{
                {"JFK", "KUL"},
                {"JFK", "NRT"},
                {"NRT", "JFK"}
        };

        List<String> itinerary = new ReconstructItinerary().findItinerary(tickets);
        itinerary.forEach(System.out::println);
    }

    private List<String> findItinerary(String[][] tickets) {

        if (tickets == null || tickets.length == 0 || tickets[0].length == 0) {
            throw new IllegalArgumentException();
        }

        Map<String, List<String>> ticketMap = new HashMap<>();
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            if (!ticketMap.containsKey(from)) {
                ticketMap.put(from, new ArrayList<>());
            }
            ticketMap.get(from).add(to);
        }

        for (String to : ticketMap.keySet()) {
            Collections.sort(ticketMap.get(to));
        }

        return findItineraryHelper("JFK", ticketMap);
    }

    private List<String> findItineraryHelper(String from, Map<String, List<String>> ticketMap) {
        List<String> itinerary = new ArrayList<>();

        if (!ticketMap.containsKey(from)) {
            return itinerary;
        }

        if (ticketMap.size() == 1 && ticketMap.get(from).size() == 1) {
            itinerary.add(from);
            itinerary.add(ticketMap.get(from).get(0));
            ticketMap.remove(from);
            return itinerary;
        }

        List<String> toList = ticketMap.get(from);

        for (int i = 0; i < toList.size(); i++) {
            String to = toList.get(i);
            toList.remove(i);
            if (toList.isEmpty()) {
                ticketMap.remove(from);
            }
            List<String> nextItinerary = findItineraryHelper(to, ticketMap);
            if (!nextItinerary.isEmpty()) {
                itinerary.add(from);
                itinerary.addAll(nextItinerary);
                break;
            }
            toList.add(i, to);
            ticketMap.put(from, toList);
        }

        return itinerary;
    }
}
