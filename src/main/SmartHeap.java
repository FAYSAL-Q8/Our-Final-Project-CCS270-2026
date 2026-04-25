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
 * Min-Heap implementation used for task scheduling.
 * Supports efficient task selection in O(log n).
 */
public class SmartHeap {
    private ArrayList<Task> heap = new ArrayList<>();
    private String strategy; // "SJF" or "EDF"

 /**
 * Creates a heap with a selected scheduling strategy.
 *
 * @param strategy the scheduling strategy ("SJF" or "EDF")
 */
    public SmartHeap(String strategy) {
        this.strategy = strategy;
    }
    
/**
 * Inserts a task into the heap.
 * The task is first added at the end, then heapifyUp is used
 * to restore the heap order.
 *
 * @param task the task to be inserted
 */
    // --- CORE ALGORITHM: INSERT ---
    public void insert(Task task) {
        heap.add(task); // Add to the end
        heapifyUp(heap.size() - 1); // Bubble it up to the right spot
    }

  /**
 * Removes and returns the next task to execute.
 * The root contains the highest-priority task based on the selected strategy.
 * After removing it, heapifyDown restores the heap order.
 *
 * @return the next task to execute, or null if the heap is empty
 */
    // --- CORE ALGORITHM: EXTRACT ---
    public Task extractNext() {
        if (heap.isEmpty()) return null;
        Task topTask = heap.get(0);
        Task lastTask = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastTask);
            heapifyDown(0); // Bubble it down to fix the tree
        }
        return topTask;
    }


    // --- HEAPIFY LOGIC ---
/**
 * Moves a newly inserted task upward until the heap property is restored.
 * This is used after insertion.
 *
 * @param index the index of the inserted task
 */
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && isMoreUrgent(heap.get(index), heap.get(parent))) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    /**
 * Moves a task downward until the heap property is restored.
 * This is used after extracting the root task.
 *
 * @param index the index where heapifyDown starts
 */
    private void heapifyDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heap.size() && isMoreUrgent(heap.get(left), heap.get(smallest))) smallest = left;
        if (right < heap.size() && isMoreUrgent(heap.get(right), heap.get(smallest))) smallest = right;

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // The Greedy Scheduling Logic

/**
 * Compares two tasks based on the selected scheduling strategy.
 * For SJF, the task with shorter duration is more urgent.
 * For EDF, the task with earlier deadline is more urgent.
 *
 * @param t1 the first task
 * @param t2 the second task
 * @return true if t1 should come before t2
 */
    private boolean isMoreUrgent(Task t1, Task t2) {
        if (strategy.equals("SJF")) return t1.duration < t2.duration;
        return t1.deadline < t2.deadline; // Default to EDF
    }

/**
 * Swaps two tasks inside the heap array.
 *
 * @param i index of the first task
 * @param j index of the second task
 */
    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

 /**
 * Checks whether the heap has no tasks.
 *
 * @return true if the heap is empty, otherwise false
 */
    public boolean isEmpty() { return heap.isEmpty(); }
}
