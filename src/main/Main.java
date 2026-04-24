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

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        while (true) {

            System.out.println("\n=== Smart Task Scheduler ===");
            System.out.println("1. Run Predefined Example");
            System.out.println("2. Run Empirical Time Complexity Analysis");
            System.out.println("3. Enter Your Own Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

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

                int[] sizes = {100, 1000, 5000, 10000, 50000};

                System.out.printf("%-10s | %-20s | %-20s\n",
                        "Size", "Heap Avg Time (ns)", "Array Avg Time (ns)");

                for (int size : sizes) {

                    long totalHeap = 0;
                    long totalArray = 0;

                    for (int trial = 0; trial < 5; trial++) {

                        SmartHeap heap = new SmartHeap("SJF");
                        SortedArray array = new SortedArray("SJF");

                        // Heap timing
                        long startHeap = System.nanoTime();
                        for (int i = 0; i < size; i++) {
                            heap.insert(new Task("T", "Low", rand.nextInt(500), rand.nextInt(100)));
                        }
                        long endHeap = System.nanoTime();

                        // Array timing
                        long startArray = System.nanoTime();
                        for (int i = 0; i < size; i++) {
                            array.insert(new Task("T", "Low", rand.nextInt(500), rand.nextInt(100)));
                        }
                        long endArray = System.nanoTime();

                        totalHeap += (endHeap - startHeap);
                        totalArray += (endArray - startArray);
                    }

                    long avgHeap = totalHeap / 5;
                    long avgArray = totalArray / 5;

                    System.out.printf("%-10d | %-20d | %-20d\n",
                            size, avgHeap, avgArray);
                }

                // ========== Explanation ==========
                System.out.println("\nNOTE:");
                System.out.println("- Heap insertion complexity: O(log n)");
                System.out.println("- Sorted Array insertion complexity: O(n)");

                continue;
            }

            Task[] tasks;

            // User Input
            if (choice == 3) {
                System.out.print("Enter number of tasks: ");

                while (!input.hasNextInt()) {
                    System.out.print("Invalid input. Enter number of tasks: ");
                    input.next();
                }
                int n = input.nextInt();
                if (n <= 0) n = 1;

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
                        System.out.print("Invalid input. Please strictly enter High, Medium, or Low: ");
                    }

                    System.out.print("Deadline: ");

                    while (!input.hasNextInt()) {
                        System.out.print("Invalid input. Please enter a number for Deadline: ");
                        input.next();
                    }
                    int deadline = input.nextInt();

                    System.out.print("Duration: ");

                    while (!input.hasNextInt()) {
                        System.out.print("Invalid input. Please enter a number for Duration: ");
                        input.next();
                    }
                    int duration = input.nextInt();

                    tasks[i] = new Task(name, priority, deadline, duration);
                }

            } else {
                tasks = new Task[]{
                        new Task("Task1", "High", 10, 5),
                        new Task("Task2", "Medium", 15, 3),
                        new Task("Task3", "Low", 20, 2)
                };
            }

            // ========== SJF ==========
            System.out.println("\n=== SJF Scheduling (Shortest Job First) ===");
            SmartHeap sjfHeap = new SmartHeap("SJF");

            for (Task t : tasks) sjfHeap.insert(t);

            while (!sjfHeap.isEmpty()) {
                System.out.print(sjfHeap.extractNext().name + " -> ");
            }
            System.out.println("END");

            // ========== EDF ==========
            System.out.println("\n=== EDF Scheduling (Earliest Deadline First) ===");
            SmartHeap edfHeap = new SmartHeap("EDF");

            for (Task t : tasks) edfHeap.insert(t);

            while (!edfHeap.isEmpty()) {
                System.out.print(edfHeap.extractNext().name + " -> ");
            }
            System.out.println("END");

        }

        input.close();
    }
}