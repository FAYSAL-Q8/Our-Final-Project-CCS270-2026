/*
Group Project : Smart Task Scheduler
Members:
FAYSAL ALIBRAHIM  ID: 2231165155
KHALED ALMARJAN   ID: 2231119438
OMAR   ALHAJRI    ID: 2231152612
Course code & section: CCS 270-02A
Supervised by Prof. Dr. Iyad Abu Doush
 */

import java.util.Scanner;
import java.util.Random;
/**
 * Main scheduler that selects and executes tasks
 * using SJF (Shortest Job First) or EDF (Earliest Deadline First) algorithms.
 * It allows the user to test different scheduling strategies (SJF & EDF),
 */
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // Random generator used in the empirical experiment
        Random rand = new Random();

        while (true) {

            System.out.println("\n=== Smart Task Scheduler ===");
            System.out.println("1. Run Predefined Example");
            System.out.println("2. Run Empirical Time Complexity Analysis");
            System.out.println("3. Enter Your Own Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            // Input validation
            while (!input.hasNextInt()) {
                System.out.print("Invalid input. Choose option 1, 2, 3 or 4:");
                input.next();
            }
            int choice = input.nextInt();

            // Exit
            if (choice == 4) {
                System.out.println("Exiting program...");
                break;
            }

            // ========== Experiment ==========
            if (choice == 2) {
                System.out.println("\n=== Empirical Time Complexity Analysis ===");
                System.out.println("We measure insertion time for Heap vs Sorted Array\n");

                int[] sizes = {100, 1000, 5000, 10000, 20000};
               // int[] sizes = {10000, 20000, 30000, 40000, 50000, 75000, 100000}; used for report
                System.out.printf("%-10s | %-20s | %-20s\n",
                        "Size", "Heap Avg Time (ns)", "Array Avg Time (ns)");

                // Loop through each input size
                for (int size : sizes) {

                    long totalHeap = 0;
                    long totalArray = 0;

                    // Repeat experiment multiple times
                    for (int trial = 0; trial < 5; trial++) {

                        SmartHeap heap = new SmartHeap("SJF");
                        SortedArray array = new SortedArray("SJF");

                        // Measure heap insertion time
                        long startHeap = System.nanoTime();
                        for (int i = 0; i < size; i++) {
                            heap.insert(new Task("T", "Low", rand.nextInt(500), rand.nextInt(100)));
                        }
                        long endHeap = System.nanoTime();

                        // Measure sorted array insertion time
                        long startArray = System.nanoTime();
                        for (int i = 0; i < size; i++) {
                            array.insert(new Task("T", "Low", rand.nextInt(500), rand.nextInt(100)));
                        }
                        long endArray = System.nanoTime();

                        // Accumulate total time
                        totalHeap += (endHeap - startHeap);
                        totalArray += (endArray - startArray);
                    }

                    // Calculate average execution time
                    long avgHeap = totalHeap / 5;
                    long avgArray = totalArray / 5;

                    System.out.printf("%-10d | %-20d | %-20d\n",
                            size, avgHeap, avgArray);
                }

                // ========== Explanation ==========
                System.out.println("\nNOTE:");
                System.out.println("- Heap insertion complexity: O(log n)");
                System.out.println("- Sorted Array insertion complexity: O(n)");

                // Return to main menu after finishing experiment
                continue;
            }

            // declare an array
            Task[] tasks;

            // User Input
            if (choice == 3) {
                System.out.print("Enter number of tasks: ");

                while (!input.hasNextInt()) {
                    System.out.print("Invalid input. Enter number of tasks: ");
                    input.next();
                }
                int n = input.nextInt();
                // Prevent zero or negative input
                if (n <= 0)
                    n = 1;

                // array with size n
                tasks = new Task[n];


                for (int i = 0; i < n; i++) {
                    System.out.println("\nTask " + (i + 1));

                    System.out.print("Name: ");
                    String name = input.next();

                    System.out.print("Priority (High/Medium/Low): ");
                    String priority = "";
                    while (true) {
                        priority = input.next();
                        if (priority.equalsIgnoreCase("High") ||
                                priority.equalsIgnoreCase("Medium") ||
                                priority.equalsIgnoreCase("Low")) {
                            break; // Input is valid, exit the loop
                        }
                        System.out.print("Invalid input. Please enter High, Medium, or Low: ");
                    }

                    // Validate deadline input
                    System.out.print("Deadline: ");
                    while (!input.hasNextInt()) {
                        System.out.print("Invalid input. Please enter a number for Deadline: ");
                        input.next();
                    }
                    int deadline = input.nextInt();

                    // Validate Duration input
                    System.out.print("Duration: ");
                    while (!input.hasNextInt()) {
                        System.out.print("Invalid input. Please enter a number for Duration: ");
                        input.next();
                    }
                    int duration = input.nextInt();

                    // Create task object
                    tasks[i] = new Task(name, priority, deadline, duration);
                }

            } else {
                // Predefined Tasks
                tasks = new Task[]{
                        new Task("Task1", "High", 10, 5),
                        new Task("Task2", "Medium", 15, 3),
                        new Task("Task3", "Low", 20, 2)
                };
            }

            // ========== SJF ==========
            System.out.println("\n=== SJF Scheduling (Shortest Job First) ===");
            // Using heap with SJF strategy
            SmartHeap sjfHeap = new SmartHeap("SJF");

            // Put all tasks inside the heap
            for (Task t : tasks) sjfHeap.insert(t);

            // Extract tasks based on shortest duration
            while (!sjfHeap.isEmpty()) {
                System.out.print(sjfHeap.extractNext().name + " -> ");
            }
            System.out.println("END");

            // ========== EDF ==========
            System.out.println("\n=== EDF Scheduling (Earliest Deadline First) ===");
            // Using heap with EDF strategy
            SmartHeap edfHeap = new SmartHeap("EDF");

            for (Task t : tasks) edfHeap.insert(t);

            // Extract tasks based on earliest deadline
            while (!edfHeap.isEmpty()) {
                System.out.print(edfHeap.extractNext().name + " -> ");
            }
            System.out.println("END");

        }

        // Close scanner
        input.close();
    }
}
