/*
Group Project : Smart Task Scheduler
Members:
FAYSAL ALIBRAHIM  ID: 2231165155
KHALED ALMARJAN   ID: 2231119438
OMAR   ALHAJRI    ID: 2231152612
Course code & section: CCS 270-02A
Supervised by Prof. Dr. Iyad Abu Doush
 */

import java.util.ArrayList;
/**
 * Sorted array implementation used for comparison with heap.
 */
public class SortedArray {
    private ArrayList<Task> list = new ArrayList<>();
    private String strategy;
    /**
     * Constructs a new SortedArray with a specified scheduling strategy.
     *
     * @param strategy The scheduling policy to use ("SJF" for Shortest Job First,
     * or "EDF" for Earliest Deadline First).
     */
    public SortedArray(String strategy) {
        this.strategy = strategy;
    }

    /**
     * Inserts a new task into the array while maintaining the sorted order.
     * The method linearly scans the array to find the correct insertion point.
     * Time Complexity: O(n) due to the traversal and subsequent memory shift
     * required by the dynamic array.
     *
     * @param task The Task object to be inserted into the schedule.
     */
    public void insert(Task task) {
        int insertIndex = 0;
        while (insertIndex < list.size() && !isMoreUrgent(task, list.get(insertIndex))) {
            insertIndex++;
        }
        list.add(insertIndex, task); // Shifting elements takes O(n) time
    }
    /**
     * Retrieves and removes the most urgent task from the front of the schedule.
     * Time Complexity: O(n) because removing from index 0 forces the underlying
     * ArrayList to shift all remaining elements one position to the left.
     *
     * @return The most urgent Task, or null if the array is empty.
     */
    public Task extractNext() {
        if (list.isEmpty()) return null;
        return list.remove(0); // Removing from index 0 takes O(n) time to shift
    }
    /**
     * Dynamically evaluates the urgency between two tasks based on the active
     * scheduling strat4egy (SJF or EDF).
     *
     * @param t1 The first Task to compare.
     * @param t2 The second Task to compare.
     * @return true if t1 is more urgent than t2; false otherwise.
     */
    private boolean isMoreUrgent(Task t1, Task t2) {
        if (strategy.equals("SJF")) return t1.duration < t2.duration;
        return t1.deadline < t2.deadline;
    }
}
