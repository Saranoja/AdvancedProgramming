/**
 * @author : Calin Irina, I2E2
 */
package HR;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class RandomProblem {
    public RandomProblem() {
    }
    
    /*
       GenerateAndSolveRandomProblem() creates a list of randomly generated residents and a list of randomly generated hospitals.
       It also creates 2 "fake" preference maps - also randomly.
       For the generated problem, it also creates a Matching and checks if the matching is stable.
    */

    public void GenerateAndSolveRandomProblem() {
        //generate fake names for residents and fake hospitals
        Faker faker = new Faker();
        Random rand = new Random();

        var fakeResident = IntStream.rangeClosed(0, 4).mapToObj(i -> new Resident(faker.name().fullName())).toArray(Resident[]::new);
        System.out.println("\n Randomly generated residents:");
        for (var i : fakeResident) {
            System.out.println("\t" + i);
        }
        var fakeHospitals = IntStream.rangeClosed(0, 2).mapToObj(i -> new Hospital(faker.name().lastName() + " Hospital", rand.nextInt(5) + 1)).toArray(Hospital[]::new);

        System.out.println("Randomly generated hospitals:");
        for (var i : fakeHospitals) {
            System.out.println("\t" + i + "  - randomly generated capacity: " + i.getCapacity());
        }
        System.out.println("\n");


        List<Resident> fakeResidentList = new ArrayList<>();
        fakeResidentList.addAll(Arrays.asList(fakeResident));

        List<Hospital> fakeHospitalList = new ArrayList<Hospital>();
        fakeHospitalList.addAll(Arrays.asList(fakeHospitals));

        //randomly generating a preference list for each resident
        Map<Resident, List<Hospital>> fakeResPrefMap = new HashMap<>();

        for (var j : fakeResident) {
            List<Hospital> toAdd = new ArrayList<>();
            List<Hospital> filter = new ArrayList<>(fakeHospitalList.size());
            filter.addAll(fakeHospitalList);
            int maxPrefs = rand.nextInt(fakeHospitalList.size());
            for (int i = 0; i <= maxPrefs; i++) {
                int chosen = rand.nextInt(filter.size());
                toAdd.add(filter.get(chosen));
                filter.remove(chosen);
            }
            fakeResPrefMap.put(j, toAdd);
        }
        System.out.println("The randomly generated preference list for the Residents:");
        System.out.println(fakeResPrefMap);
        System.out.println("\n");

        //randomly generating a preference list for each hospital
        Map<Hospital, List<Resident>> fakeHosPrefMap = new Hashtable<>();

        for (var j : fakeHospitals) {
            List<Resident> toAdd = new ArrayList<>();
            List<Resident> filter = new ArrayList<>(fakeResidentList.size());
            filter.addAll(fakeResidentList);
            int maxPrefs = rand.nextInt(fakeResidentList.size());
            for (int i = 0; i <= maxPrefs; i++) {
                int chosen = rand.nextInt(filter.size());
                toAdd.add(filter.get(chosen));
                filter.remove(chosen);
            }
            fakeHosPrefMap.put(j, toAdd);
        }
        System.out.println("The randomly generated preference list for the Hospitals:");
        System.out.println(fakeHosPrefMap);
        System.out.println("\n");

        Matching fake = new Matching();
        Map<Resident, Hospital> model = fake.CreateMatching(fakeResPrefMap);

        fake.IsStable(fakeResPrefMap, fakeHosPrefMap, fakeHospitalList, fakeResidentList, model);
    }
}
