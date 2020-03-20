import java.util.*;

public class Solution {
    private Problem problem;

    public Solution(Problem problem) {
        this.problem = problem;
    }

    /** simple algorithm for allocating trips to vehicles
     * @param problem the problem with all the parameters
     */

    public void getSolution(Problem problem) {
        ArrayList<Client> clientArray = new ArrayList<Client>();
        ArrayList<Client> clientArray2 =  new ArrayList<Client>();
        Set<Integer> mySet = new HashSet<Integer>();
        for (Client c : this.problem.getClients()) {
            //System.out.println(c.getOrder());
            int size=mySet.size();
            mySet.add(c.getOrder());
            int newSize=mySet.size();
            if(newSize>size)
                clientArray.add(c);
        }

        System.out.println(mySet);
        System.out.println("One greedy solution for the problem is: ");
        //System.out.println(mySet);
        System.out.println(clientArray);
    }
}
