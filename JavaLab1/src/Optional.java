//Author: Irina Calin, I2E2

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Optional {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Optional app = new Optional();
        int n = 0, k = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception err) {
            System.out.println("Wrong type. Try again.");
        }
        try {
            k = Integer.parseInt(args[1]);
        } catch (Exception err) {
            System.out.println("Wrong type. Try again.");
        }

        if (n != 0) System.out.println("The number of words is " + n);
        if (k != 0) System.out.println("The number of characters is " + k);
        String alphabet[] = new String[args.length - 2];
        for (int i = 0; i < args.length - 2; i++)
            alphabet[i] = args[i + 2];
        int nbWords = n; //how many words to generate
        String words[] = app.generate(nbWords, alphabet, k);

        System.out.println("The alphabet is:");
        for (String i : alphabet)
            System.out.print(i + " ");
        System.out.println("");
        System.out.println("The words are:");
        for (String i : words)
            System.out.print(i + " ");
        System.out.println("");

        //Adjacency matrix for the words
        int[][] mtx = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (check(words[i], words[j], k) == true)
                    mtx[i][j] = 1;
                else
                    mtx[i][j] = 0;

        System.out.println("The adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(mtx[i][j]);
            System.out.println("");
        }

        int max = -1;
        int min = n + 1;
        int sum = 0;
        int current = 0;
        int[] noNeighbours = new int[n];
        int all;
        boolean allEqual = true;
        for (int i = 0; i < n; i++) {
            {
                for (int j = 0; j < n; j++)
                    sum += mtx[i][j];
                if (sum > max) {
                    max = sum;
                }
                if (sum < min) {
                    min = sum;
                }
            }
            //System.out.println("The sum for word number " + i + " is " + sum);
            noNeighbours[i] = sum;
            sum = 0;
        }

        all = noNeighbours[0];

        //System.out.println("Neighbours number array: ");
        for (int i : noNeighbours) {
            if (i == max)
                System.out.println("Word with maximum number of neighbours: " + words[current]);
            if (i == min)
                System.out.println("Word with minimum number of neighbours: " + words[current]);
            current++;
            if (all != i)
                allEqual = false;
        }

        if (allEqual)
            System.out.println("All the words have the same number of neighbours");
        else
            System.out.println("Not all the words have the same number of neighbours");

        long endTime = System.nanoTime();
        long timeDifference = endTime - startTime;
        System.out.println("Running time: " + timeDifference + " nanoseconds");

        int noVertices = 0;
        int[] connected = new int[n];
        int[] mark = new int[n];
        do {
            if (connected[noVertices] == 0) {
                mark = BFS(mtx, n, noVertices);

                System.out.print("Connected component: " + noVertices + " ");

                for (int j = 0; j < n; ++j)
                    if (mark[j] == 1 && j != noVertices) {
                        System.out.print(j + " ");
                        connected[j] = 1;
                    }
                System.out.println("");
            }
            noVertices++;
        } while (noVertices < n);
    }

    public String[] generate(int n, String[] alphabet, int noChars) {
        String[] words = new String[n];
        int length = 0;
        //StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            while (length < noChars) {
                int pos = (int) (Math.random() * (alphabet.length + 1)) - 1;
                if (pos > 0) {
                    sb.append(alphabet[pos]);
                    length++;
                }
            }
            words[i] = sb.toString();
            length = 0;
        }
        return words;
    }

    public static boolean check(String word1, String word2, int chars) {
        char[] temp1 = word1.toCharArray();
        char[] temp2 = word2.toCharArray();
        for (int i = 0; i < chars; i++) {
            for (int j = 0; j < chars; j++) {
                //System.out.println("I am comparing characters " + temp1[i] + " and " + temp2[j] + " from words " + word1 + " and " + word2);
                if (temp1[i] == temp2[j]) {
                    //System.out.println("Found a match for " + temp1[i] + " and " + temp2[j] + " on words " + word1 + " and " + word2);
                    return true;
                }
            }
        }
        System.out.println("No match for words " + word1 + " and " + word2);
        return false;
    }

    public static int[] BFS(int[][] adjacencyMatrix, int vertexCount, int startVertex) {
        // Result array.
        int[] mark = new int[vertexCount];

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(startVertex);
        mark[startVertex] = 1;

        while (!queue.isEmpty()) {
            Integer current = queue.remove();

            for (int i = 0; i < vertexCount; ++i)
                if (adjacencyMatrix[current][i] == 1 && mark[i] == 0) {
                    mark[i] = 1;
                    queue.add(i);
                }
        }

        return mark;
    }
}