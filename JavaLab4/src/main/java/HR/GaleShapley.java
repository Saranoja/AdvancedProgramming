/**
 * @author : Calin Irina, I2E2
 */
package HR;

import java.util.*;

/* Implemented the Gale-Shapley Algorithm for solving the HR Stable Matching Problem
   However, from what I've read, this algorithm requires the number of elements (residents) be equal to the number of partitions (hospitals).
   Otherwise, a problem may have multiple stable matchings, not all of the same size.
   Also, each element should rank every single partition and vice-versa. Therefore, I will use such an example in order to
   illustrate the functionality.
*/

public class GaleShapley {
    private List<Resident> residentList;
    private List<Hospital> hospitalList;
    private Map<Resident, List<Hospital>> resPrefMap;
    private Map<Hospital, List<Resident>> hosPrefMap;

    public GaleShapley(List<Resident> residentList, List<Hospital> hospitalList, Map<Resident, List<Hospital>> resPrefMap, Map<Hospital, List<Resident>> hosPrefMap) {
        this.residentList = residentList;
        this.hospitalList = hospitalList;
        this.resPrefMap = resPrefMap;
        this.hosPrefMap = hosPrefMap;
    }

    public void GetMatch() {
        //adding all the hospitals to a HashSet, so it's easy to lookup remaining free hospitals
        Set<Resident> availableResidents = new HashSet<Resident>(residentList);

        //Store hospital allocation
        Map<Hospital, List<Resident>> availableHospitals = new HashMap<Hospital, List<Resident>>();
        for (Hospital h : hospitalList)
            availableHospitals.put(h, null); //null means no resident was allocated
        int size = availableHospitals.size();
        Resident currentResident = new Resident();
        //loop until there's an unassigned resident
        while (size > 0) { //we do this until everyone is assigned
            currentResident = availableResidents.iterator().next();
            System.out.println("\nResident " + currentResident + " arrives:");
            ArrayList<Resident> temp = new ArrayList<Resident>();
            ArrayList<Resident> tempNew = new ArrayList<Resident>();

            for (Hospital h : resPrefMap.get(currentResident)) { //loop through the preferences of this resident
                //System.out.println("His preference: " + h);
                ArrayList<Resident> matchedResidents = new ArrayList<Resident>();
                matchedResidents.addAll(availableHospitals.get(h)); //see who is already assigned to that hospital

                if (matchedResidents.size() < h.getCapacity()) //the hospital still has space
                {
                    temp.add(currentResident);
                    availableHospitals.put(h, temp); //add the newcomer to the hospital's list
                    h.decreaseCapacity();
                    availableResidents.remove(currentResident); //mark that the resident has been assigned
                    System.out.println("Resident: " + currentResident + " was assigned to " + h);
                    break;
                } else //the hospital is full
                {
                    int prefForMatchedResident = hosPrefMap.get(matchedResidents).indexOf(currentResident);
                    int prefForLastResident = hosPrefMap.get(matchedResidents).size() - 1;
                    if (prefForLastResident > prefForMatchedResident) //if the resident that comes has a better position
                    {
                        try {
                            tempNew.remove(tempNew.get(tempNew.size() - 1)); //remove the last one on the list
                        } catch (Exception e) {
                        }
                        tempNew.add(currentResident);
                        availableHospitals.put(h, tempNew); //update the map
                        availableResidents.remove(currentResident); //update
                        availableResidents.addAll(matchedResidents); //accept current resident and dump the assigned one
                        System.out.println("Resident " + matchedResidents + "dumped by Hospital " + h);
                        System.out.println("Resident " + currentResident + " got assigned now to " + h);
                        break;
                    }
                }
            }
            size = availableResidents.size();
        }

        Iterator<Map.Entry<Hospital, List<Resident>>> itr = availableHospitals.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Hospital, List<Resident>> entry = itr.next();
            System.out.println("Resident/s " + entry.getValue() + " assigned to " + entry.getKey());
        }
    }
}
