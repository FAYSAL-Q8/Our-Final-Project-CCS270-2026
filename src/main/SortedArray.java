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

public class SortedArray {
    private ArrayList<Task> list = new ArrayList<>();
    private String strategy;

    public SortedArray(String strategy) {
        this.strategy = strategy;
    }

    // O(n) Insertion - We scan the list to find the right spot
    public void insert(Task task) {
        int insertIndex = 0;
        while (insertIndex < list.size() && !isMoreUrgent(task, list.get(insertIndex))) {
            insertIndex++;
        }
        list.add(insertIndex, task); // Shifting elements takes O(n) time
    }

    public Task extractNext() {
        if (list.isEmpty()) return null;
        return list.remove(0); // Removing from index 0 takes O(n) time to shift
    }

    private boolean isMoreUrgent(Task t1, Task t2) {
        if (strategy.equals("SJF")) return t1.duration < t2.duration;
        return t1.deadline < t2.deadline;
    }
}