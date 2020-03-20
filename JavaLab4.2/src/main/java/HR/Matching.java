/**
 * @author : Calin Irina, I2E2
 */
package HR;

import java.util.*;

public class Matching {
    Map<Resident, Hospital> match = new HashMap<>();

    public Matching() {
    }

    /*
       CreateMatching(...): For each key resident R in resPrefMap, we create a matching between R and the first hospital H which is not at full capacity
       Note: there exists, in cas of a randomly generated problem, the case in which such a matching does not exist.
       Example: R0=[H0,H1,H2],R1=[H0,H3],R2=[H0] and capacity(H0)=2
       One solution for this problem is to assign residents according to the size of their preference list (one who's got the least should be assigned first)
       But again... first COME, first served. This way, some residents might remain unallocated.
    */

    public Map<Resident, Hospital> CreateMatching(Map<Resident, List<Hospital>> resPrefMap) throws ArrayIndexOutOfBoundsException {
        int i = 0;
        try {
            for (Resident resident : resPrefMap.keySet()) {
                while (resPrefMap.get(resident).get(i).getCapacity() == 0)
                    ++i;
                if (resPrefMap.get(resident).get(i).getCapacity() > 0) {
                    match.put(resident, resPrefMap.get(resident).get(i));
                    //System.out.println("Resident: " + resident);
                    //System.out.println("Hospital: " + resPrefMap.get(resident).get(i));
                    resPrefMap.get(resident).get(i).decreaseCapacity();
                }
            }
            System.out.println("A matching is " + match);
        } catch (Exception e) {
            System.out.println("There is no way to assign one (or more) of the residents due to the reduced capacity of one hospital " +
                    "- mainly because of the randomness of the problem and the *first come - first served algorithm." +
                    " Some residents cannot get served this way. A solution would be serving the residents according to the number of their preferences (increasingly)." +
                    "\nThere might be some residents who only have one preferred hospital which is already full. Try generating again.");
        }
        return match;
    }

    /*
       IsStable(...): let R be any key resident in the matching map. We should take the corresponding hospital H and check its index in R's preference list
       If H is not the first one, then we should check the hospitals which come before it.
       For each hospital H# in this situation, we search their preference list and see if R is in there. We then have to check all the residents R#
       that come AFTER R in order. If at least one of these residents was assigned to H, the matching is not stable (we have a situation in which R was
       assigned to H, but R prefers H# better and H# prefers R rather than some R# that was actually assigned to it.)
       In any other case (no such situation can be found), the matching is stable.
       Note: In most cases, due to the randomness of the input and the large enough capacity of each hospital, the matching is stable,
       since each resident is assigned directly to the preferred hospital.
    */

    public boolean IsStable(Map<Resident, List<Hospital>> resPrefMap, Map<Hospital, List<Resident>> hosPrefMap, List<Hospital> hospitalSet, List<Resident> residentList, Map<Resident, Hospital> matching) {
        if (matching.size() == resPrefMap.size()) {
            for (Resident resident : matching.keySet()) {
                //debugging help
                //System.out.println("\n Hospitals that have " + resident + " as their top preference:");
                //hospitalSet.stream().filter(hos -> hosPrefMap.get(hos).get(0).equals(resident)).forEach(System.out::println);

                int indexOfMatchingHospitalInResPref = resPrefMap.get(resident).indexOf(matching.get(resident));

                //System.out.println("\nIndex of hospital " + matching.get(resident) + " in the list of " + resident + " is " + indexOfMatchingHospitalInResPref);

                Hospital betterPreferredHospital = new Hospital();
                if (indexOfMatchingHospitalInResPref > 0)
                    for (int i = 1; i <= indexOfMatchingHospitalInResPref; i++) {
                        betterPreferredHospital = resPrefMap.get(resident).get(indexOfMatchingHospitalInResPref - i);
                        int indexOfResidentInHospitalList = hosPrefMap.get(betterPreferredHospital).indexOf(resident);
                        if (indexOfResidentInHospitalList != hosPrefMap.get(betterPreferredHospital).size() - 1)
                            for (int j = hosPrefMap.get(betterPreferredHospital).size() - 1; j > indexOfResidentInHospitalList; --j)
                                if (matching.get(hosPrefMap.get(betterPreferredHospital).get(j)).equals(betterPreferredHospital)) {
                                    System.out.println("The matching is not stable. The pair which does not respect the rule is " + resident + " with " + matching.get(resident));
                                    return false;
                                }
                        //matchedResidentsForHospital.addAll(matching.keySet().stream().filter(local->matching.get(local).equals(betterPreferredHospital)).collect(Collectors.toList()));
                    }
            }
            System.out.println("The matching is stable");
        } else System.out.println("No matching exists in order to state whether it's stable of not.");
        return true;
    }
}
