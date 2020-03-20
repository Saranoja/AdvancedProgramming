package KnapsackProblem;//Author: Calin Irina, I2E2
import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        Item book1 = new Book("Dragon Rising", 300, 5);
        Item book2 = new Book("A Blade in the Dark", 300, 5);
        Item food1 = new Food("Cabbage", 2);
        Item food2 = new Food("Rabbit", 2);
        Item weapon = new Weapon(Type.SWORD, 5, 10);
        List<Item> items = new ArrayList<Item>();
        items.add(book1);
        items.add(book2);
        items.add(food1);
        items.add(food2);
        items.add(weapon);
        //items.add
        Knapsack k = new Knapsack(10);
        System.out.println("Capacity of the knapsack: " + k.getCapacity());

        System.out.println("Instance of the problem: ");
        for (Item i : items)
            System.out.println(i);

        Greedy solve = new Greedy(k, items);
        solve.FillKnapsack();

        Knapsack k2 = new Knapsack(10);

        DynamicProg solve2 = new DynamicProg(k2, items);
        solve2.FillKnapsack();

        System.out.println("...................");
        Problem pb = new Problem();
        System.out.println(pb);
        System.out.println("...................");
        System.out.println("The greedy solution for my random problem is:");
        Greedy randomSol = new Greedy(pb.getKnapsack(), pb.getItems());
        randomSol.FillKnapsack();
        System.out.println("...................");

        Knapsack k3 = new Knapsack(10);
        Problem graphTry = new Problem(items,k3);
        GraphModel graph = new GraphModel(graphTry);

        try {
            //you don't want to print such a large matrix
            //graph.printMatrix();
            graph.getSolution();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
