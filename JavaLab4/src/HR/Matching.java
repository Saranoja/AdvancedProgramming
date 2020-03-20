package HR;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Matching {
    Map<Resident, Hospital> match = new HashMap<>();

    public Matching() {

    }

    public Map<Resident, Hospital> CreateMatching(Map<Resident, List<Hospital>> resPrefMap) {
        int i = 0;
        //System.out.println(resPrefMap);
        for (Resident resident : resPrefMap.keySet()) {
            while (resPrefMap.get(resident).get(i).getCapacity() == 0)
                ++i;
            if (resPrefMap.get(resident).get(i).getCapacity() > 0) {
                match.put(resident, resPrefMap.get(resident).get(i));
                // System.out.println(resident);
                resPrefMap.get(resident).get(i).DecreaseCapacity();
            }
        }

        System.out.println("The matching is " + match);
        return match;
    }
}