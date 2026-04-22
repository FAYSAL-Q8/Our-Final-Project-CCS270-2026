public class Main {
    public static void main(String[] args) {
        // 1. Setup the example tasks
        Task t1 = new Task("Task 1", "High", 10, 5);
        Task t2 = new Task("Task 2", "Medium", 15, 3);
        Task t3 = new Task("Task 3", "Low", 20, 2);

        // 2. Output SJF Order
        System.out.println("Execution order (SJF):");
        SmartHeap sjfHeap = new SmartHeap("SJF");
        sjfHeap.insert(t1); sjfHeap.insert(t2); sjfHeap.insert(t3);
        while (!sjfHeap.isEmpty()) {
            System.out.print(sjfHeap.extractNext().name + (sjfHeap.isEmpty() ? "\n" : " -> "));
        }

        // 3. Output EDF Order
        System.out.println("Execution order (EDF):");
        SmartHeap edfHeap = new SmartHeap("EDF");
        edfHeap.insert(t1); edfHeap.insert(t2); edfHeap.insert(t3);
        while (!edfHeap.isEmpty()) {
            System.out.print(edfHeap.extractNext().name + (edfHeap.isEmpty() ? "\n" : " -> "));
        }

        // 4. Run the Empirical Testing (5 Trials)
        System.out.println("\n--- Empirical Time Complexity Analysis ---");
        int[] sizes = {100, 1000, 5000};
        System.out.printf("%-10s | %-15s | %-15s\n", "Input Size", "Heap Avg (ns)", "Array Avg (ns)");

        for (int size : sizes) {
            long totalHeapTime = 0, totalArrayTime = 0;

            for (int trial = 0; trial < 5; trial++) {
                SmartHeap heap = new SmartHeap("SJF");
                SortedArray array = new SortedArray("SJF");

                // Time the Heap
                long startHeap = System.nanoTime();
                for (int i = 0; i < size; i++) heap.insert(new Task("T", "Low", 10, 5));
                totalHeapTime += (System.nanoTime() - startHeap);

                // Time the Array
                long startArray = System.nanoTime();
                for (int i = 0; i < size; i++) array.insert(new Task("T", "Low", 10, 5));
                totalArrayTime += (System.nanoTime() - startArray);
            }

            System.out.printf("%-10d | %-15d | %-15d\n", size, totalHeapTime / 5, totalArrayTime / 5);
        }
    }
}