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

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== Smart Task Scheduler ===");
            System.out.println("1. Run Predefined Example");
            System.out.println("2. Enter Your Own Tasks");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = input.nextInt();

            // Exit
            if (choice == 3) {
                System.out.println("Exiting program...");
                break;
            }

            Task[] tasks;

            // User Input
            if (choice == 2) {
                System.out.print("Enter number of tasks: ");
                int n = input.nextInt();
                tasks = new Task[n];

                for (int i = 0; i < n; i++) {
                    System.out.println("\nTask " + (i + 1));

                    System.out.print("Name: ");
                    String name = input.next();

                    System.out.print("Priority: ");
                    String priority = input.next();

                    System.out.print("Deadline: ");
                    int deadline = input.nextInt();

                    System.out.print("Duration: ");
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

            // ========== Experiment ==========
            System.out.println("\n=== Empirical Time Complexity Analysis ===");
            System.out.println("We measure insertion time for Heap vs Sorted Array\n");

            int[] sizes = {100, 1000, 5000};

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
                        heap.insert(new Task("T", "Low", 10, 5));
                    }
                    long endHeap = System.nanoTime();

                    // Array timing
                    long startArray = System.nanoTime();
                    for (int i = 0; i < size; i++) {
                        array.insert(new Task("T", "Low", 10, 5));
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


        }
    }

}

