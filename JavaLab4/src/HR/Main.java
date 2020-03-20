//Author: Calin Irina, I2E2
/* An instance of HR involves a set of residents and a set f hospitals, each resident seeking a post at one hospital,
and each hospital having a number of available posts (its capacity). Each resident ranks some (acceptable) hospitals in
strict order, and each hospital ranks its applicants in strict order. A matching is a set of pairs (resident, hospital)
such that each resident is assigned to at most one hospital and the capacities of the hospitals are not exceeded.
A matching is stable if there is no pair (r, h) such that r is assigned to h' but r prefers h better than h'
and h prefers r better than some of its assigned residents. We consider the problem of creating a stable matching
between residents and hospitals.*/
package HR;
import HR.Hospital;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var r = IntStream.rangeClosed(0, 3).mapToObj(i -> new Resident("R" + i)).toArray(Resident[]::new);
        var h = IntStream.rangeClosed(0, 3).mapToObj(i -> new Hospital("H" + i)).toArray(Hospital[]::new);

        List<Resident> residentList = new ArrayList<>();
        //List<HR.Hospital> hospitalList = new ArrayList<>();

        residentList.addAll(Arrays.asList(r));

        Set<Hospital> hospitalSet = new TreeSet<Hospital>();

        hospitalSet.addAll(Arrays.asList(h));

        List<Resident> newSortedList = residentList.stream().sorted(Comparator.comparing(Resident::getName)).collect(Collectors.toList());

        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();
        Map<Hospital, List<Resident>> hosPrefMap = new Hashtable<>();

        resPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[2], Arrays.asList(h[0], h[1]));
        resPrefMap.put(r[3], Arrays.asList(h[0], h[2]));

        hosPrefMap.put(h[0], Arrays.asList(r[0], r[1], r[2], r[3]));
        hosPrefMap.put(h[1], Arrays.asList(r[0], r[1], r[2]));
        hosPrefMap.put(h[2], Arrays.asList(r[0], r[1], r[3]));

        System.out.println(resPrefMap);
        System.out.println(hosPrefMap);

        System.out.println("Residents who find acceptable H0 and H2");
        List<Hospital> target = Arrays.asList(h[0], h[2]);
        List<Resident> result = residentList.stream().filter(res -> resPrefMap.get(res).containsAll(target)).collect(Collectors.toList());
        System.out.println(result);
        System.out.println("Hospitals that have R0 as their top preference");
        try {
            hospitalSet.stream().filter(hos -> hosPrefMap.get(hos).get(0).equals(r[0])).forEach(System.out::println);
        } catch (Exception e) {
            //System.out.println("Null Pointer Exception");
        }
        //residentList.stream().filter(res -> resPrefMap.get(res).contains(h[0])).forEach(System.out::println);

        Matching matching=new Matching();
        matching.CreateMatching(resPrefMap);
    }
}
